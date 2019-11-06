package com.dalaoyang.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopInvokeHandle implements InvocationHandler {
    private Aspect aspect;
    private Object target;

    public AopInvokeHandle(Aspect aspect, Object target) {
        super();
        this.aspect = aspect;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(this.aspect.getPointcut().matchMethod(method)){
            return this.aspect.getAdvice().invoke(target,method,args);
        }
        return method.invoke(target,args);
    }

}
