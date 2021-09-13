package cn.codeforfun.client.handler;

import cn.codeforfun.client.annotation.DiscoveryClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Slf4j
public class ReflectiveClient implements InvocationHandler {

    private Class<?> type;
    private String name;
    private String url;

    private Map<Method, MethodHandle> dispatch = new LinkedHashMap<>();

    public ReflectiveClient() {
    }

    public static ReflectiveClient build(Class<?> client) {
        ReflectiveClient result = new ReflectiveClient();
        String serviceName = client.getAnnotation(DiscoveryClient.class).value();
        result.setType(client);
        result.setName(serviceName);
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("ip:{}, name:{}, url:{}", InetAddress.getLocalHost().getHostAddress(), getName(), getUrl());
        Map<Method, MethodHandle> dispatch = getDispatch();
        int size = dispatch.size();
        log.info("dispatch size:{}", size);
        Class<?> type = getType();
        DiscoveryClient annotation = type.getAnnotation(DiscoveryClient.class);
        return null;
    }
}
