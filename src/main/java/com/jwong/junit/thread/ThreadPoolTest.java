package com.jwong.junit.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jwong 2017年10月20日 16:24:47
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        // 固定线程池
        // ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 缓存线程池
        // ExecutorService threadPool = Executors.newCachedThreadPool();

        // 单一线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            final int task = i;
            threadPool.execute(() -> {
                for (int i1 = 1; i1 <= 10; i1++) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is loop of " + i1 + " for task of " + task);
                }
            });
        }
        System.out.println("all of 10 tasks had committed...");
        threadPool.shutdown();

        /**
         * 10秒开始
         */
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bomb...");
            }
        }, 10, TimeUnit.SECONDS);

        /**
         * 12秒开始, 每隔2秒一次
         */
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("bomb...");
            }
        }, 12, 2, TimeUnit.SECONDS);

    }

}
