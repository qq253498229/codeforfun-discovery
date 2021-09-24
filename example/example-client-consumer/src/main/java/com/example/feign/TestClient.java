package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@DiscoveryClient("client-producer")
//@FeignClient(value = "test-client",url = "http://localhost:30081")
@FeignClient(value = "test-client",url = "${feign.test-client.url:}")
public interface TestClient {
    @GetMapping("/test1")
    String test1();
}
