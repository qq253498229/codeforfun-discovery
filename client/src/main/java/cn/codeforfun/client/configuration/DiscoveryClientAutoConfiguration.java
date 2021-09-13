package cn.codeforfun.client.configuration;

import cn.codeforfun.client.annotation.EnableDiscoveryClient;
import cn.codeforfun.client.annotation.DiscoveryClient;
import cn.codeforfun.client.bean.ReflectiveClient;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

@Configuration
public class DiscoveryClientAutoConfiguration implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // get main configuration class
        EnableDiscoveryClient annotation = getConfiguration(beanFactory);
        if (annotation == null) {
            return;
        }
        // scan all service client
        List<Class<?>> clientList = getClient(annotation);
        // register these clients into beanFactory
        registerClients(beanFactory, clientList);
    }

    private EnableDiscoveryClient getConfiguration(ConfigurableListableBeanFactory beanFactory) {
        // get bean with annotation "EnableDiscoveryClient"
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(EnableDiscoveryClient.class);
        if (beansWithAnnotation.isEmpty()) {
            return null;
        }
        // fixme
        // get first configuration class
        Map.Entry<String, Object> configurationClass = beansWithAnnotation.entrySet().iterator().next();
        // return this annotation
        return AnnotationUtils.findAnnotation(configurationClass.getValue().getClass(), EnableDiscoveryClient.class);
    }

    private List<Class<?>> getClient(EnableDiscoveryClient annotation) {
        List<Class<?>> result = new ArrayList<>();
        // get base packages string array
        String[] basePackages = annotation.basePackages();
        // iterator it
        for (String basePackage : basePackages) {
            Reflections reflections = new Reflections(basePackage,
                    new SubTypesScanner(false),
                    new TypeAnnotationsScanner(),
                    new FieldAnnotationsScanner());
            // get class set with annotation "DiscoveryClient"
            Set<Class<?>> classes = new HashSet<>(reflections.getTypesAnnotatedWith(DiscoveryClient.class));
            // add to result
            result.addAll(classes);
        }
        return result;
    }

    private void registerClients(ConfigurableListableBeanFactory beanFactory, List<Class<?>> clientList) {
        if (ObjectUtils.isEmpty(clientList)) {
            return;
        }
        for (Class<?> client : clientList) {
            String simpleName = client.getSimpleName();
            String beanName = simpleName.substring(0, 1).toLowerCase().concat(simpleName.substring(1));

            // build instance
            ReflectiveClient clientInstance = ReflectiveClient.build(client);
            // convert instance to proxy
            Object proxy = getProxy(client, clientInstance);
            // register this proxy into spring bean factory
            beanFactory.registerSingleton(beanName, proxy);
        }
    }

    public <T> T getProxy(Class<T> clazz, InvocationHandler handler) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, handler);
    }
}
