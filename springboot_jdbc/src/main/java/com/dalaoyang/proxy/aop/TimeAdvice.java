package com.dalaoyang.proxy.aop;

import java.lang.reflect.Method;

public class TimeAdvice implements Advice {

    @Override
    public Object invoke(Object target, Method method, Object[] args) throws Exception {
        long stime = System.currentTimeMillis();
        Object ret = method.invoke(target,args);
        long useTime = System.currentTimeMillis();
        System.out.println("记录"+ target.getClass()+"."+method.getName()+"耗时间："+((float)(useTime-stime)/1000));
        return ret;
    }
}
