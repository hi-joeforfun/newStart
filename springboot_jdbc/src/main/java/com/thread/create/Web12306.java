package com.thread.create;
/**
 * 方便共享资源
 * @author Administrator
 *
 */
public class Web12306 implements Runnable {
	private  int  num = 10;

	@Override
	public void run() {
		while(true){
			if(num<=0){
				break; //跳出循环
			}
			num = num -1;
			System.out.println(Thread.currentThread().getName()+"抢到了"+num);
		}
	}

	public static void main(String[] args) {
		//真实角色
		Web12306 web = new Web12306();
		//代理
		Thread t1 =new Thread(web,"路人甲");
		Thread t2 =new Thread(web,"黄牛已");
		Thread t3 =new Thread(web,"攻城师");
		Thread t4 =new Thread(web,"1路人甲");
		Thread t5 =new Thread(web,"2路人甲");
		Thread t6 =new Thread(web,"3路人甲");

		//启动线程
		t1.start();
		t2.start();
		t3.start();
	}
}
