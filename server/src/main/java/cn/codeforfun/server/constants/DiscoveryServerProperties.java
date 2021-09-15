package cn.codeforfun.server.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "discovery.server")
public class DiscoveryServerProperties {
    /**
     * 健康检查间隔。单位: 秒
     */
    private Integer healthCheckInterval = 5;
}
