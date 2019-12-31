package com.thread.ThreadPoolDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class FixedSizeThreadPool {
    //     抛异常  返回布尔     阻塞    超时
    //插入 add       offer      put     off(e,time)
    //移除 remove    poll      take     poll（time）
    public BlockingQueue<Task> blockingQueue;

    public List<Thread> workers;

    //初始化线程池 线程数 与 任务数量
    public  FixedSizeThreadPool(int poolSize, int taskSize){
        if(poolSize <=0 || taskSize <=0){
            throw new IllegalArgumentException("非法参数");
        }
        this.blockingQueue = new LinkedBlockingQueue<>(taskSize);
        this.workers = Collections.synchronizedList(new ArrayList<>());//线程安全的线程数组
        for (int i = 0;i < poolSize; i++ ){//循环构造 workers 让线程开始工作start()
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    //5.提交一个任务到线程池化线程池
    public boolean submit(Task task){
        if(this.isWorking){
            return this.blockingQueue.offer(task);
        }else {
            return false;
        }

    }

    //6.关闭线程池
    //a.让线程池停止接受任务
    //b.把线程池的任务执行完毕
    //c.关闭后 继续执行未完成的任务的时候，必须非阻塞
    //d.已经阻塞的线程，关闭即可
    public volatile boolean isWorking = true;
    public void shutDown(){
        this.isWorking = false;
        for(Thread worker:workers){
            if(Thread.State.BLOCKED.equals(worker.getState())){
                worker.interrupt();//中断线程
            }
        }
    }

    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3,6);

        for(Integer i = 0;i < 6; i++ ){//放入6个任务
            Task task = new Task(i.toString());
            pool.submit(task
//                    new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("放入一个任务");
//                    try{
//                        Thread.sleep(2000L);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
            );
        }
        pool.shutDown();
    }

}
