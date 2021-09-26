package cn.codeforfun.client.configuration;

import cn.codeforfun.client.constants.DiscoveryServiceProperties;
import cn.codeforfun.client.data.DataContext;
import cn.codeforfun.client.data.DataHandler;
import cn.codeforfun.client.data.ServiceInstance;
import cn.codeforfun.client.exception.ServiceNameNotFoundException;
import cn.hutool.core.net.NetUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

@Configuration
@Import(DiscoveryServiceProperties.class)
@Slf4j
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
    }

    private void startRefreshServiceListSchedule() {

    }

    private void startActiveCurrentServiceSchedule() {
        Integer serviceActiveInterval = discoveryServiceProperties.getServiceActiveInterval();
        String cron = "*/" + serviceActiveInterval + " * * * * *";
        CronUtil.schedule(cron, (Task) () -> {
            ServiceInstance serviceInstance = getServiceInstance();
            dataHandler.activeService(serviceInstance);
            log.debug("Active current service, name: {}, host:{}, port:{}", serviceInstance.getName(), serviceInstance.getHost(), serviceInstance.getPort());
        });
        CronUtil.setMatchSecond(true);
        CronUtil.start();
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
