package com.example.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DiscoveryConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public Object initial() {
        return null;
    }
}
