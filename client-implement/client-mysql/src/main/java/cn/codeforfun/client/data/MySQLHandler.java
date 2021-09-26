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
        String sql = "insert into cff_service (name, host, port, last_active) value (?, ?, ?, now()) " +
                "on duplicate key update last_active=now()";
        jdbcTemplate.update(sql, serviceInstance.getName(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    @Override
    public void activeService(ServiceInstance serviceInstance) {
        String sql = "insert into cff_service (name, host, port, last_active) value (?, ?, ?, now()) " +
                "on duplicate key update last_active=now()";
        jdbcTemplate.update(sql, serviceInstance.getName(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    @Override
    public List<ServiceInstance> findServiceInstanceList(Integer serviceActiveTimeout) {
        long time = DateUtil.offsetSecond(new Date(), -serviceActiveTimeout).getTime();
        String sql = "select id, name, remark, host, port, last_active from cff_service where last_active > ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setName(rs.getString("name"));
            serviceInstance.setRemark(rs.getString("remark"));
            serviceInstance.setHost(rs.getString("host"));
            serviceInstance.setPort(rs.getInt("port"));
            serviceInstance.setLastActive(new Date(rs.getTimestamp("last_active").getTime()));
            return serviceInstance;
        }, time);
    }
}
