package cn.codeforfun.server.data;

import cn.codeforfun.server.constants.DiscoveryServerProperties;
import lombok.Data;

@Data
public class DataContext {
    private DataHandler dataHandler;
    private DiscoveryServerProperties discoveryServerProperties;

    public void start() {
        // todo
        System.out.println(1);
    }


}
