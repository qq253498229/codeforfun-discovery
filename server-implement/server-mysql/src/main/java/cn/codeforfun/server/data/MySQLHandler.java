package cn.codeforfun.server.data;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

public class MySQLHandler implements DataHandler {
    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public int registerService(MicroService microService) {
        return 0;
    }

    @Override
    public List<MicroService> findServerList() {
        return null;
    }

    @Override
    public List<MicroService> findServiceList() {
        return null;
    }
}
