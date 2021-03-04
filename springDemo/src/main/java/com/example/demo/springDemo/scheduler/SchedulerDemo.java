package com.example.demo.springDemo.scheduler;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class SchedulerDemo {
    public static void main(String[] args) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
		scheduler.initialize();
        ScheduledFuture<?> future = scheduler.schedule(new Runnable() {
			@Override
			public void run() {
                System.out.println(Thread.currentThread().getName());
                while(true){}
            }
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				return new CronTrigger("0/1 * * * * ? ").nextExecutionTime(triggerContext);
			}
		});

    }
}
