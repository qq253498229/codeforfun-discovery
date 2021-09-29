package cn.codeforfun.client.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "cff-service")
public class ServiceInstanceForElasticsearch {
    @Id
    private String id;

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
     * 上次激活时间
     */
    private Date lastActive;
}
