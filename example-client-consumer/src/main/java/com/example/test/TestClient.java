package com.example.test;

import cn.codeforfun.client.annotation.ServiceClient;
import org.springframework.web.bind.annotation.GetMapping;

@ServiceClient("client-producer")
public interface TestClient {
    @GetMapping("/test1")
    String test1();
}
