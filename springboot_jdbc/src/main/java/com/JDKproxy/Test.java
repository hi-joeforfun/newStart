package com.JDKproxy;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        BeProxy beProxy = new BeproxyImp();
        BeProxy afterProxy = DynamicProxy.getProxy(beProxy,beProxy.getClass());
        afterProxy.test();
    }
}
