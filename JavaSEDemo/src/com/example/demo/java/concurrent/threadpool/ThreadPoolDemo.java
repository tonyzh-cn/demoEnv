package com.example.demo.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService ex = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 5; i++) {
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			ex.submit(new Runnable() {

				@Override
				public void run() {
					for(int j=0;j<10;j++) {
						System.out.println(Thread.currentThread().getName()+"-"+j);
					}
				}
			});
		}
		ex.shutdown();
	}
}
