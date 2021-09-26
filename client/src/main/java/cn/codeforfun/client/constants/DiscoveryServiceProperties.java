package cn.codeforfun.client.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "discovery.service")
public class DiscoveryServiceProperties {
    /**
     * 服务激活间隔。单位: 秒
     * 每隔多长时间激活一次自身服务
     */
    private Integer serviceActiveInterval = 10;
    /**
     * 刷新服务列表间隔。单位: 秒
     * 每隔多长时间刷新一次服务列表
     */
    private Integer refreshServiceListInterval = 20;
    /**
     * 服务激活超时时间。单位: 秒
     * 超过这个时间后该服务就会被认为是有故障的，之后就不会调用这个服务
     */
    private Integer serviceActiveTimeout = 60;
    /**
     * 服务名。如果没设置的话会取 (spring.application.name) 的值
     */
    private String name;
    /**
     * 服务端口。如果没设置的话会取 (server.port) 的值，如果还是没有则默认8080
     */
    private Integer port;
    /**
     * 服务地址。如果没设置会取本地ip
     */
    private String host;
}
