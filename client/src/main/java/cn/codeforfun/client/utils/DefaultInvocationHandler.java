package cn.codeforfun.client.utils;

import java.lang.reflect.Method;

public class DefaultInvocationHandler extends InvocationHandlerWithTarget {
    public DefaultInvocationHandler(Object target) {
        super(target);
    }

    @Override
    public Object invoke(final Object o, final Method method, final Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
