package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "client-producer", fallback = TestClientFallback.class)
public interface TestClient {
    @GetMapping("/test1")
    String test1();
}
