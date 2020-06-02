//package com.thread.AQS;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.CountDownLatch;
//
//
//
//
//class Mysql{
//    List<String> list = new ArrayList<>();
//
//    public List<String> getList() {
//        return list;
//    }
//
//    public void setList(List<String> list) {
//        this.list = list;
//    }
//
//    public List<String> getListBatch(int start,int end){
//        List<String> subList = new ArrayList<>();
//        for(int i=start;i<end;i++){
//            subList.add(this.list.get(i));
//        }
//        return subList;
//    }
//
//    public void update(List<String> list , int start , int end ){
//        for(int i=start;i<end;i++){
//            this.list.set(i,list.get(i));
//        }
//    }
//
//}
//
//public class Sample {
//
//    /**
//     * 计数器，用来控制线程
//     * 传入参数2，表示计数器计数为2
//     */
//
//
//
//
//    List<String> list = new ArrayList<>();
//
//    private final static CountDownLatch mCountDownLatch = new CountDownLatch(5);
//
//    /**
//     * A线程类
//     */
//    private static class ThreadA extends Thread {
//
//        public List<String> list;
//
//        @Override
//        public void run() {
//            System.out.println("开始统计数字  出发!");
//            try {
//                // 会阻塞在这里等待 mCountDownLatch 里的count变为0；
//                // 也就是等待另外的WorkingThread调用countDown()
//                System.out.println(list.size());
//                mCountDownLatch.await();
//            } catch (InterruptedException e) {
//
//            }
//            for(String s:list){
////                if(!s.equals("xp")){
////                    System.out.println("error !!!");
////                }
//                System.out.println(s);
//            }
//            System.out.println("统计结束  到终点拉!");
//        }
//    }
//
//    /**
//     * BC线程类
//     */
//    private static class WorkingThread implements Callable<List> {
//        private final String mThreadName;
//        private final int mSleepTime;
//        private int start;
//        private int end;
//        public Mysql mysql;
//        public WorkingThread(String name, int sleepTime,int start,int end,Mysql mysql) {
//            mThreadName = name;
//            mSleepTime = sleepTime;
//            this.start = start;
//            this.end = end;
//            this.mysql = mysql;
////            data = data;
//        }
////
////        @Override
////        public void run() {
////            System.out.println("[" + mThreadName + "] 出发 加工!");
////            try {
////                Thread.sleep(mSleepTime);
////                for(int i = this.start ; i < this.end ; i++){
////                    String xp = list.get(i);
////                    xp = xp + "p";
////                }
////
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////
////            System.out.println("[" + mThreadName + "] 加工 结束!");
////            mCountDownLatch.countDown();
////        }
//
//        @Override
//        public List call() throws Exception {
//            System.out.println("[" + mThreadName + "] 出发 加工!");
//            try {
//                Thread.sleep(mSleepTime);
//                for(int i = this.start ; i < this.end ; i++){
//                    String xp = mysql.getList().get(i);
//                    xp = xp + "p";
//                }
////                for(String s:this.list){
////                    s = s + "p";
////                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("[" + mThreadName + "] 加工 结束!");
//            mCountDownLatch.countDown();
//            return  this.list;
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        Mysql data = new Mysql();
//        for(Integer i = 0 ;i<10000;i++){
////            data.getList().add("x");
//            data.list.add("x");
//        }
//
//        // 最先run ThreadA
//        new ThreadA().start();
//
//        for(Integer i = 0 ;i<5;i++){
//            new WorkingThread(i + "选手", 0,i*2000,(i+1)*2000,data).start();
//
//        }
//
//
////        new WorkingThread("B选手", 100).start();
////
////        new WorkingThread("C选手", 100).start();
//
//    }
//
//}