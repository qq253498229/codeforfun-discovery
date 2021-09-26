package cn.codeforfun.client.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MySQLHandler implements DataHandler {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public int registerService(ServiceInstance serviceInstance) {
        return 0;
    }

    @Override
    public List<ServiceInstance> findServerList() {
        return null;
    }

    @Override
    public List<ServiceInstance> findServiceList() {
        return null;
    }
}
