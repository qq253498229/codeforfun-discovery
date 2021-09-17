package cn.codeforfun.client.data;

import java.util.List;

/**
 * 服务处理接口，需要手动实现
 */
public interface DataHandler {
    int registerService(MicroService microService);

    /**
     * 获取服务（服务端）列表
     *
     * @return 服务端列表
     */
    List<MicroService> findServerList();

    /**
     * 获取服务（客户端）列表
     *
     * @return 客户端列表
     */
    List<MicroService> findServiceList();
}
