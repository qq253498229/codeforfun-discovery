package cn.codeforfun.server.data;

import cn.codeforfun.server.constants.DiscoveryServerProperties;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DataContext implements Runnable {
    @Resource
    DiscoveryServerProperties discoveryServerProperties;

    private DataHandler dataHandler;

    @Override
    public void run() {
        CronUtil.schedule("", (Task) () -> {
            System.out.println(1);
        });
    }

    public DataContext(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        run();
    }

    private DataContext() {
    }
}
