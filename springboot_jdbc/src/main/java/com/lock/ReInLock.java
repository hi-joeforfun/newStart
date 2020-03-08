package com.lock;

public class ReInLock {
    boolean isLocked = false;
    Thread  lockedBy = null;//记录当前线程的引用
    int lockedCount = 0;//记录当前被锁次数
    public synchronized void lock()
            throws InterruptedException{
        Thread thread = Thread.currentThread();
        while(isLocked && lockedBy != thread){//其他线程 进入就会wait()
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }
    public synchronized void unlock(){
        if(Thread.currentThread() == this.lockedBy){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                notify();
            }
        }
    }
}
