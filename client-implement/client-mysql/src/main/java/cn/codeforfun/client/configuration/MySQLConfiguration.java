package cn.codeforfun.client.configuration;

import cn.codeforfun.client.data.DataHandler;
import cn.codeforfun.client.data.MySQLHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySQLConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DataHandler dataHandler() {
        return new MySQLHandler();
    }
}
