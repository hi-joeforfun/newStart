package com.dalaoyang.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class AopInvokeHandle implements InvocationHandler {
    private Aspect aspect;
    private Object target;
    //拿到切面与目标对象
    public AopInvokeHandle(Aspect aspect, Object target) {
        super();
        this.aspect = aspect;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(this.aspect.getPointcut().matchMethod(method)){//看是否切中目标对象的具体方法，如果切中 就返回该对象
            return this.aspect.getAdvice().invoke(target,method,args);// method 就是 luluse的方法
        }
        return method.invoke(target,args);
    }

}
