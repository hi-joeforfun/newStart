package com.thread.ThreadPoolDemo;

public class Task extends Thread {
    String name;
    @Override
    public void run(){
        System.out.println("任务" + name);
    }
    Task(String name){
        this.name = name;
    }

}
