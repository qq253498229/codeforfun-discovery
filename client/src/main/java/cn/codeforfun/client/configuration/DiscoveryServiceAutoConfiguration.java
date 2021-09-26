package cn.codeforfun.client.configuration;

import cn.codeforfun.client.constants.DiscoveryServiceProperties;
import cn.codeforfun.client.data.DataContext;
import cn.codeforfun.client.data.DataHandler;
import cn.codeforfun.client.data.ServiceInstance;
import cn.codeforfun.client.exception.ServiceNameNotFoundException;
import cn.hutool.core.net.NetUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Configuration
@Import(DiscoveryServiceProperties.class)
public class DiscoveryServiceAutoConfiguration {
    @Resource
    private Environment environment;
    @Resource
    private DataHandler dataHandler;
    @Resource
    private DataContext dataContext;
    @Resource
    private DiscoveryServiceProperties discoveryServiceProperties;

    @PostConstruct
    public void start() {
        registerCurrentService();
        startActiveCurrentServiceSchedule();
        startRefreshServiceListSchedule();
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

    @PreDestroy
    public void stop() {
        CronUtil.stop();
        deregisterCurrentService();
    }

    private void deregisterCurrentService() {
        ServiceInstance serviceInstance = getServiceInstance();
        dataHandler.deregisterService(serviceInstance);
    }

    private void startRefreshServiceListSchedule() {
        Integer refreshServiceListInterval = discoveryServiceProperties.getRefreshServiceListInterval();
        String cron = "*/" + refreshServiceListInterval + " * * * * *";
        Integer serviceActiveTimeout = discoveryServiceProperties.getServiceActiveTimeout();
        CronUtil.schedule(cron, (Task) () -> refreshServiceInstanceList(serviceActiveTimeout));
    }

    private void refreshServiceInstanceList(Integer serviceActiveTimeout) {
        List<ServiceInstance> serviceInstanceList = dataHandler.findServiceInstanceList(serviceActiveTimeout);
        if (!ObjectUtils.isEmpty(serviceInstanceList)) {
            dataContext.refreshServiceInstances(serviceInstanceList);
        }
    }

    private void startActiveCurrentServiceSchedule() {
        Integer serviceActiveInterval = discoveryServiceProperties.getServiceActiveInterval();
        String cron = "*/" + serviceActiveInterval + " * * * * *";
        CronUtil.schedule(cron, (Task) this::activeCurrentService);
    }

    private void activeCurrentService() {
        ServiceInstance serviceInstance = getServiceInstance();
        dataHandler.activeService(serviceInstance);
    }

    private void registerCurrentService() {
        ServiceInstance serviceInstance = getServiceInstance();
        dataHandler.registerService(serviceInstance);
    }

    private ServiceInstance getServiceInstance() {
        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setName(getApplicationName());
        serviceInstance.setHost(getHost());
        serviceInstance.setPort(getPort());
        return serviceInstance;
    }

    private String getHost() {
        String host;
        if (discoveryServiceProperties.getHost() != null) {
            host = discoveryServiceProperties.getHost();
        } else {
            host = NetUtil.getLocalhostStr();
        }
        return host;
    }

    private Integer getPort() {
        Integer port = 8080;
        if (discoveryServiceProperties.getPort() != null) {
            port = discoveryServiceProperties.getPort();
        } else if (environment.getProperty("server.port") != null) {
            port = Integer.valueOf(Objects.requireNonNull(environment.getProperty("server.port")));
        }
        return port;
    }

    private String getApplicationName() {
        String applicationName;
        if (discoveryServiceProperties.getName() != null) {
            applicationName = discoveryServiceProperties.getName();
        } else if (environment.getProperty("spring.application.name") != null) {
            applicationName = environment.getProperty("spring.application.name");
        } else {
            throw new ServiceNameNotFoundException("Service name not found, you must set discovery.service.name or spring.application.name first.");
        }
        return applicationName;
    }

}
