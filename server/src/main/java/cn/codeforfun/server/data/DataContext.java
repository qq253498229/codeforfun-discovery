package cn.codeforfun.server.data;

import cn.codeforfun.server.constants.DiscoveryServerProperties;
import cn.codeforfun.server.exception.ServiceNameNotFoundException;
import lombok.Data;
import org.springframework.core.env.Environment;

@Data
public class DataContext {
    private Environment environment;
    private DataHandler dataHandler;
    private DiscoveryServerProperties discoveryServerProperties;

    public void start() {
        startRegister();
        startVoteTask();
        startServerTask();
    }

    /**
     * 注册服务
     */
    private void startRegister() {
        MicroService microService = new MicroService();
        String applicationName = discoveryServerProperties.getName() == null ? environment.getProperty("spring.application.name") : discoveryServerProperties.getName();
        if (applicationName == null) {
            throw new ServiceNameNotFoundException("");
        }
        dataHandler.registerService(microService);
    }

    /**
     * 开启选举任务
     */
    public void startVoteTask() {
    }

    /**
     * 开启服务端任务
     */
    private void startServerTask() {
    }
}
