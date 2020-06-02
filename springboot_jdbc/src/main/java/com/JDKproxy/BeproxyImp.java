package com.JDKproxy;

public class BeproxyImp implements BeProxy {
    public void test() throws InterruptedException {
        System.out.println("in");
        Thread.sleep(1000);
        System.out.println("out");
    }
}

