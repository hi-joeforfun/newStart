package com.thread.ThreadPoolDemo;

public class Worker extends Thread{//工作者
    private FixedSizeThreadPool fixedSizeThreadPool;//工作者属于一个线程池

    public Worker(FixedSizeThreadPool fixedSizeThreadPool){
        this.fixedSizeThreadPool = fixedSizeThreadPool;
    }

    @Override
    public void run(){//工作者把线程池的所有任务全部take
        //只有在线程池在工作，或者 线程池有需要处理的任务的时候 工作者都需要一直工作
        while (fixedSizeThreadPool.isWorking || this.fixedSizeThreadPool.blockingQueue.size() > 0 ){
            Task task = null;
            try{
                if(fixedSizeThreadPool.isWorking){
                    task = this.fixedSizeThreadPool.blockingQueue.take();//取出任务，阻塞类型的取出 取不到就会阻塞
                }else{
                    task = this.fixedSizeThreadPool.blockingQueue.poll();//取出任务，为关闭线程池后，工作线程执行完任务task后，关闭线程，非阻塞类型的取出
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            if(task != null){
                task.run();
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行" + task.name);
            }

        }
    }
}
