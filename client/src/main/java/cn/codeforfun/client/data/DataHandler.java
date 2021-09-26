package cn.codeforfun.client.data;

/**
 * 服务处理接口，需要手动实现
 */
public interface DataHandler {
    /**
     * 注册服务
     *
     * @param serviceInstance 服务示例对象
     */
    void registerService(ServiceInstance serviceInstance);

    /**
     * 激活服务
     *
     * @param serviceInstance 服务示例对象
     */
    void activeService(ServiceInstance serviceInstance);
}
