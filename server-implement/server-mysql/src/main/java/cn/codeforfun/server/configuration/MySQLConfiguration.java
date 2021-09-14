package cn.codeforfun.server.configuration;

import cn.codeforfun.server.data.DataHandler;
import cn.codeforfun.server.data.MySQLHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySQLConfiguration {
    @Bean
    public DataHandler dataHandler() {
        return new MySQLHandler();
    }
}
