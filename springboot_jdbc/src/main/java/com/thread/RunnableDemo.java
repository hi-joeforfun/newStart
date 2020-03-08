package com.thread;

public class RunnableDemo {

    public static class TestRunnable implements  Runnable{
        @Override
        public void run(){
            System.out.println("一段代码。。。");
        }

        public static void main(String[] args) {
            TestRunnable testRunnable = new TestRunnable();
            new Thread(testRunnable).start();
            System.out.println("master");
            System.out.println("dev");
            System.out.println("dev");
            (new Runnable() {
                @Override
                public void run() {
                    System.out.println("放入一个任务");
                    try{
                        Thread.sleep(2000L);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).run();

        }
    }
}
