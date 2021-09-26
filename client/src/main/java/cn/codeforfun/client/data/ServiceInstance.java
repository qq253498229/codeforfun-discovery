package cn.codeforfun.client.data;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceInstance {
    private Long id;
    /**
     * 服务名
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 上次活跃时间
     */
    private Date lastActive;
}
