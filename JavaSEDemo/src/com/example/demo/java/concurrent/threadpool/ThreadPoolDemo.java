package com.example.demo.java.concurrent.threadpool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ThreadPoolExecutor ex = new ThreadPoolExecutor(5, 5,
				1L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());

//		ex.allowCoreThreadTimeOut(true);

		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ex.submit(new Runnable() {

				@Override
				public void run() {
					for(int j=0;j<1;j++) {
						System.out.println(Thread.currentThread().getName()+"-"+j);
					}
				}
			});
		}

		ex.shutdown();
	}
}
