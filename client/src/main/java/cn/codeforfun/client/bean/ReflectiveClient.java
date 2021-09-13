package cn.codeforfun.client.bean;

import cn.codeforfun.client.annotation.ServiceClient;
import lombok.Data;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ReflectiveClient implements InvocationHandler {

    private Class<?> type;
    private String name;
    private String url;

    private Map<Method, MethodHandle> dispatch = new LinkedHashMap<>();

    public ReflectiveClient() {
    }

    public static ReflectiveClient build(Class<?> client) {
        ReflectiveClient result = new ReflectiveClient();
        String serviceName = client.getAnnotation(ServiceClient.class).value();
        result.setType(client);
        result.setName(serviceName);
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<Method, MethodHandle> dispatch = getDispatch();
        Class<?> type = getType();
        String name = getName();
        String url = getUrl();
        ServiceClient annotation = type.getAnnotation(ServiceClient.class);
        return null;
    }
}
