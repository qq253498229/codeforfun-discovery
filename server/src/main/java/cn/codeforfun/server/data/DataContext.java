package cn.codeforfun.server.data;

import cn.codeforfun.server.constants.DiscoveryServerProperties;
import lombok.Data;

@Data
public class DataContext {
    private DataHandler dataHandler;
    private DiscoveryServerProperties discoveryServerProperties;

    public void start() {
        startVoteTask();
        startServerTask();
    }

    /**
     * 开启选举任务
     */
    public void startVoteTask() {
        System.out.println(1);
    }

    /**
     * 开启服务端任务
     */
    private void startServerTask() {
    }
}
