package cn.codeforfun.client.configuration;

import cn.codeforfun.client.constants.DiscoveryServiceProperties;
import cn.codeforfun.client.data.DataHandler;
import cn.codeforfun.client.data.MicroService;
import cn.codeforfun.client.exception.ServiceNameNotFoundException;
import cn.hutool.core.net.NetUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

@Configuration
@Import(DiscoveryServiceProperties.class)
public class DataContextConfiguration {
    @Resource
    private Environment environment;
    @Resource
    private DataHandler dataHandler;
    @Resource
    private DiscoveryServiceProperties discoveryServerProperties;

    @PostConstruct
    public void start() {
        startRegister();
    }

    /**
     * 注册服务
     */
    private void startRegister() {
        MicroService microService = new MicroService();
        String applicationName = discoveryServerProperties.getName() == null ? environment.getProperty("spring.application.name") : discoveryServerProperties.getName();
        if (applicationName == null) {
            throw new ServiceNameNotFoundException("Service name not found, you must set discovery.server.name or spring.application.name first.");
        }
        Integer port = 8080;
        if (discoveryServerProperties.getPort() != null) {
            port = discoveryServerProperties.getPort();
        } else if (environment.getProperty("server.port") != null) {
            port = Integer.valueOf(Objects.requireNonNull(environment.getProperty("server.port")));
        }
        microService.setName(applicationName);
        microService.setHost(NetUtil.getLocalhostStr());
        microService.setPort(port);
        dataHandler.registerService(microService);
    }

}
