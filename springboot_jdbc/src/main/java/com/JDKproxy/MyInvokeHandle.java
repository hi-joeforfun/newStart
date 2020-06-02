package com.JDKproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokeHandle implements InvocationHandler {

    private Object target;

    public MyInvokeHandle(Object object){
        this.target = object;
    }

    //该 invoke方法是吧该对象的所有方法都进行代理
    @Override//Object proxy代理后的对象   Method method 代理的方法  Object[] args 传参
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long stime = System.currentTimeMillis();
        Object ret = method.invoke(target,args);
        long useTime = System.currentTimeMillis();
        System.out.println("记录"+ this.target.getClass()+"."+method.getName()+"耗时间："+(useTime-stime/1000));
        return ret;
    }
}
