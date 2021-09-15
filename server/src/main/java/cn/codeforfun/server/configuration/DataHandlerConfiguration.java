package cn.codeforfun.server.configuration;

import cn.codeforfun.server.constants.DiscoveryServerProperties;
import cn.codeforfun.server.data.DataContext;
import cn.codeforfun.server.data.DataHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class DataHandlerConfiguration {
    @Resource
    DiscoveryServerProperties discoveryServerProperties;
    @Resource
    DataHandler dataHandler;
    @Resource
    DataContext dataContext;

    @PostConstruct
    @ConditionalOnBean(DataContext.class)
    public void startDataContext() {
        dataContext.setDataHandler(dataHandler);
        dataContext.setDiscoveryServerProperties(discoveryServerProperties);
        dataContext.start();
    }

}
