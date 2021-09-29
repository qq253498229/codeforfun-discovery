package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.feign")
public class ElasticSearchConsumerClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchConsumerClientApplication.class, args);
    }
}
