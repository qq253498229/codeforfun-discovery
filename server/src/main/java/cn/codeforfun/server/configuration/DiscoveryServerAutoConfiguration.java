package cn.codeforfun.server.configuration;

import cn.codeforfun.server.annotation.EnableDiscoveryServer;
import cn.hutool.core.net.NetUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Map;

@Configuration
public class DiscoveryServerAutoConfiguration implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // get main annotation
        EnableDiscoveryServer annotation = getConfiguration(beanFactory);
        if (annotation == null) {
            return;
        }
        String localhostIp = NetUtil.getLocalhostStr();
        System.out.println(localhostIp);
    }

    private EnableDiscoveryServer getConfiguration(ConfigurableListableBeanFactory beanFactory) {
        // get bean with annotation "EnableDiscoveryServer"
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(EnableDiscoveryServer.class);
        if (beansWithAnnotation.isEmpty()) {
            return null;
        }
        // fixme
        // get first configuration class
        Map.Entry<String, Object> configurationClass = beansWithAnnotation.entrySet().iterator().next();
        // return this annotation
        return AnnotationUtils.findAnnotation(configurationClass.getValue().getClass(), EnableDiscoveryServer.class);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }
}
