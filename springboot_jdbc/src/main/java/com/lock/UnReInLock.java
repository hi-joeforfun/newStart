package com.lock;

public class UnReInLock {
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException{//synchronized锁住对象 该对象的获取不能
        while(isLocked){
            wait();
        }
        isLocked = true;//被唤醒时 把锁释放
    }
    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}
