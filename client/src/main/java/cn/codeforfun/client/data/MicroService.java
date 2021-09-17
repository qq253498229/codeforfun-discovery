package cn.codeforfun.client.data;

import lombok.Data;

import java.util.Date;

@Data
public class MicroService {
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
     * 是否是服务端。
     * 默认：否
     */
    private Boolean isServer;
    /**
     * 是否是主机。
     * 只有是服务端的时候才能是主机
     */
    private Boolean isMaster;
    /**
     * 上次活跃时间
     */
    private Date lastActive;
}
