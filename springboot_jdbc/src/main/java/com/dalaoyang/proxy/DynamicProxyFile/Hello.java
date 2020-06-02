package com.dalaoyang.proxy.DynamicProxyFile;

public class Hello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello joe!");
    }

    @Override
    public void sayHello(String s) {
        System.out.println("Hello " +s);
    }
}