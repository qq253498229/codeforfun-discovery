package cn.codeforfun.client.utils;

import java.lang.reflect.InvocationHandler;

public abstract class InvocationHandlerWithTarget implements InvocationHandler {
    protected final Object target;

    public InvocationHandlerWithTarget(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }
}

