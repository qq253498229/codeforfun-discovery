package cn.codeforfun.server.data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLHandler implements DataHandler {

    @Override
    public List<MicroService> findServerList() {
        return null;
    }

    @Override
    public List<MicroService> findServiceList() {
        return null;
    }
}
