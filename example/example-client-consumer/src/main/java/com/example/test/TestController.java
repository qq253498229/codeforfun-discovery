package com.example.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    TestClient testClient;

    @GetMapping("/test1")
    public String test1() {
        return "consumer client test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return testClient.test1();
    }
}
