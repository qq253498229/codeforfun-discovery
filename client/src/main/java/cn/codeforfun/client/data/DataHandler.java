package cn.codeforfun.client.data;

import java.util.List;

/**
 * 服务处理接口，需要手动实现
 */
public interface DataHandler {
    /**
     * 注册服务
     *
     * @param serviceInstance 服务实例
     */
    void registerService(ServiceInstance serviceInstance);

    /**
     * 激活服务
     *
     * @param serviceInstance 服务实例
     */
    void activeService(ServiceInstance serviceInstance);

    /**
     * 查询服务实例列表
     *
     * @return 服务实例列表
     */
    List<ServiceInstance> findServiceInstanceList(Integer serviceActiveTimeout);

    /**
     * 注销服务，用于服务关闭时调用
     *
     * @param serviceInstance 服务实例
     */
    void deregisterService(ServiceInstance serviceInstance);
}
