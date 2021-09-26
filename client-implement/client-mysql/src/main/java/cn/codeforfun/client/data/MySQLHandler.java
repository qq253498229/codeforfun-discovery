package cn.codeforfun.client.data;

import cn.hutool.core.date.DateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class MySQLHandler implements DataHandler {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public void registerService(ServiceInstance serviceInstance) {
        String sql = "insert ignore into cff_service (name, host, port, last_active) value ('"
                + serviceInstance.getName() + "', '" + serviceInstance.getHost()
                + "', " + serviceInstance.getPort() + ", now())";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void activeService(ServiceInstance serviceInstance) {
        String sql = "insert ignore into cff_service (name, host, port, last_active) value ('"
                + serviceInstance.getName() + "', '" + serviceInstance.getHost()
                + "', " + serviceInstance.getPort() + ", now())";
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<ServiceInstance> findServiceInstanceList(Integer serviceActiveTimeout) {
        long time = DateUtil.offsetSecond(new Date(), -serviceActiveTimeout).getTime();
        String sql = "select * from cff_service where last_active > " + time;
        List<ServiceInstance> serviceInstanceList = jdbcTemplate.queryForList(sql, ServiceInstance.class);
        return null;
    }
}
