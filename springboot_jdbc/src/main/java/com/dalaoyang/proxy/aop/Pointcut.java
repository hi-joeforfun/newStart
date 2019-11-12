package com.dalaoyang.proxy.aop;

import java.lang.reflect.Method;


public class Pointcut {
    private String classPattern;

    private String methodPattern;


    public Pointcut(String classPattern, String methodPattern) {
        this.classPattern = classPattern;
        this.methodPattern = methodPattern;
    }

    public boolean matchClass(Class<?> clazz){
        return clazz.getName().matches(classPattern);
    }

    public boolean matchMethod(Method method){
        return method.getName().matches(methodPattern);
    }

    public String getClassPattern() {
        return classPattern;
    }

    public void setClassPattern(String classPattern) {
        this.classPattern = classPattern;
    }

    public String getMethodPattern() {
        return methodPattern;
    }

    public void setMethodPattern(String methodPattern) {
        this.methodPattern = methodPattern;
    }
}
