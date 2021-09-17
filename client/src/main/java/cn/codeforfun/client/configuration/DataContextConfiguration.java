package cn.codeforfun.client.configuration;

import cn.codeforfun.client.constants.DiscoveryServerProperties;
import cn.codeforfun.client.data.DataContext;
import cn.codeforfun.client.data.DataHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@Import(DiscoveryServerProperties.class)
public class DataContextConfiguration {
    @Resource
    DiscoveryServerProperties discoveryServerProperties;
    @Resource
    DataHandler dataHandler;
    @Resource
    DataContext dataContext;
    @Resource
    Environment environment;

    @PostConstruct
    @ConditionalOnBean(DataContext.class)
    public void startDataContext() {
        dataContext.setDataHandler(dataHandler);
        dataContext.setDiscoveryServerProperties(discoveryServerProperties);
        dataContext.setEnvironment(environment);
        dataContext.start();
    }
}
