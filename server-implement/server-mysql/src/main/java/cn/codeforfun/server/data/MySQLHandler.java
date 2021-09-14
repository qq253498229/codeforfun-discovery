package cn.codeforfun.server.data;

import java.util.List;

public class MySQLHandler implements DataHandler {

    public MySQLHandler() {
        System.out.println(1);
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
