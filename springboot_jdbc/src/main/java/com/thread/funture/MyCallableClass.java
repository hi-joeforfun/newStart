package com.thread.funture;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个任务类，实现Callable接口
 */
public  class MyCallableClass implements Callable<String> {
    // 标志位
    private AtomicInteger flag = new AtomicInteger(0);



    public AtomicInteger getFlag() {
        return flag;
    }

    public void setFlag(AtomicInteger flag) {
        this.flag = flag;
    }



    public String call() throws Exception {
        if (this.flag.get() == 0) {
            // 如果flag的值为0，则立即返回
            return "flag = 0";
        }
        if (this.flag.get() == 1) {
            // 如果flag的值为1，做一个无限循环
            try {
                while (true) {
                    System.out.println("looping......");
                    //sleep 中断当前线程
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "false";
        } else {
            // falg不为0或者1，则抛出异常
            throw new Exception("Bad flag value!");
        }
    }
}