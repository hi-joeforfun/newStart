package com.dalaoyang.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static <T> T getProxy(Object target,Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),target.getClass().getInterfaces(),
                new MyInvokeHandle(target));//new MyInvokeHandle(target) 具体的代理增强操作的操作 业务
    }
}
