package com.dalaoyang.proxy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeConsumingStatistics {

    @Around("execution(* com.dalaoyang.service.*.*(..))")
    public Object methodTimeConsumingStatistics(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
        long stime = System.currentTimeMillis();
        Object ret = proceedingJoinPoint.proceed();
        long useTime = System.currentTimeMillis();
        System.out.println("记录"+ proceedingJoinPoint.getTarget()+"."+proceedingJoinPoint.getSignature()+"耗时间："+(useTime/1000));
        return ret;
    }
}
