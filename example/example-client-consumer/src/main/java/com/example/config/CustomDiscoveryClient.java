package com.example.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomDiscoveryClient implements DiscoveryClient {

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return null;
    }

    @Override
    public List<String> getServices() {
        return null;
    }
}
