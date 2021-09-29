package com.example.feign;

import org.springframework.stereotype.Component;

@Component
public class TestClientFallback implements TestClient {
    @Override
    public String test1() {
        return "error";
    }
}
