package com.jwong.junit.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟赛跑过程!!!
 * Created by jwong on 2017/10/24.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final int cpCount = 1;  // 裁判数量
        final int palyerCount = 5;  // 运动员数量
        final CountDownLatch countDownLatch1 = new CountDownLatch(cpCount); // 裁判计数器
        final CountDownLatch countDownLatch2 = new CountDownLatch(palyerCount); // 运动员计数器

        for (int i = 1; i <= palyerCount; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "正在准备接收命令...");    // 准备起跑

                        countDownLatch1.await();

                        System.out.println("线程" + Thread.currentThread().getName() + "回应命令, 正在处理...");  // 跑步

                        Thread.sleep((long) (Math.random() * 10000));

                        System.out.println("线程" + Thread.currentThread().getName() + "回应命令, 处理完毕...");  // 到达终点

                        countDownLatch2.countDown();

                    } catch (Exception e) {
                    }
                }
            };
            executorService.execute(runnable);
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令...");  // 准备发送起步信号

            countDownLatch1.countDown();    // 倒计时 减1   // 发送起步信号

            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令, 正在等待结果...");   // 发送起步信号

            countDownLatch2.await();

            System.out.println("线程" + Thread.currentThread().getName() + "收到全部响应结果, 全部结束...");  // 运动员全部跑到终点

        } catch (Exception e) {

        }
        executorService.shutdown();

    }
}
