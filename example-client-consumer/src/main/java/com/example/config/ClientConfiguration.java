package com.example.config;

import cn.codeforfun.client.annotation.EnableServiceClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableServiceClient({"com.example.test"})
public class ClientConfiguration {
}
