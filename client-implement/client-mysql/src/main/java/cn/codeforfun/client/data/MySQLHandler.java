package cn.codeforfun.client.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MySQLHandler implements DataHandler {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public void registerService(ServiceInstance serviceInstance) {
    }

    @Override
    public void activeService(ServiceInstance serviceInstance) {
    }
}
