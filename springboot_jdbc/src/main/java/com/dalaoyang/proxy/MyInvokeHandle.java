package com.dalaoyang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokeHandle implements InvocationHandler {

    private Object target;

    public MyInvokeHandle(Object object){
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long stime = System.currentTimeMillis();
        Object ret = method.invoke(target,args);
        long useTime = System.currentTimeMillis();
        System.out.println("记录"+ this.target.getClass()+"."+method.getName()+"耗时间："+(useTime/1000));
        return ret;
    }
}
