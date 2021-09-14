package cn.codeforfun.server.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discovery.server")
public class DiscoveryServerProperties {
    /**
     * 健康检查间隔。单位: 秒
     */
    private Integer healthCheckInterval = 5;
}
