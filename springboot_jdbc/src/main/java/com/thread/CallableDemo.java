package com.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException,InterruptedException{
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Random random = new Random();
                return "返回值是"+ random.nextInt(200);
            }
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        String result = futureTask.get();
        System.out.println(result);
    }
}
