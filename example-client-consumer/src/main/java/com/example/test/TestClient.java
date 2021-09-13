package com.example.test;

import cn.codeforfun.client.annotation.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;

@DiscoveryClient("client-producer")
public interface TestClient {
    @GetMapping("/test1")
    String test1();
}
