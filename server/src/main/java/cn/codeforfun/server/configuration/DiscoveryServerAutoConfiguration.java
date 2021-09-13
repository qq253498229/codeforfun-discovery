package cn.codeforfun.server.configuration;

import cn.hutool.core.net.NetUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscoveryServerAutoConfiguration {
    @Bean
    public void test() {
        String localhostIp = NetUtil.getLocalhostStr();
        System.out.println(localhostIp);
    }

}
