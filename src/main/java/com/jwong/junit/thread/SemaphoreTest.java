package com.jwong.junit.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号灯
 * Created by jwong on 2017/10/23.
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final int count = 3;
        Semaphore semaphore = new Semaphore(count); // 信号灯
        for (int i = 1; i <= 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程: " + Thread.currentThread().getName() + "进入, 当前已有 " + (count - semaphore.availablePermits()) + " 个并发");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                    }
                    System.out.println("线程: " + Thread.currentThread().getName() + "即将离开...");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                    }
                    System.out.println("线程: " + Thread.currentThread().getName() + "已经离开, 当前已有 " + (count - semaphore.availablePermits()) + " 个并发");
                    semaphore.release();
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

    }
}
