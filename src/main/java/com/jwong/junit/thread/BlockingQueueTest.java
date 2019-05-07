package com.jwong.junit.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 队列阻塞
 * Created by jwong on 2017/10/24.
 */
public class BlockingQueueTest {

    public static void main(String[] args) {

        final BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        for (int i = 1; i <= 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep((long) (Math.random() * 10000));
                            System.out.println("线程" + Thread.currentThread().getName() + "准备放数据...");
                            blockingQueue.put(1);
                            System.out.println("线程" + Thread.currentThread().getName() + "已经放了数据...队列目前有" + blockingQueue.size() + "个数据");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // 休眠100 / 1000更换试试, 看效果
                        Thread.sleep(100);
                        System.out.println("线程" + Thread.currentThread().getName() + "准备取数据...");
                        blockingQueue.take();
                        System.out.println("线程" + Thread.currentThread().getName() + "已经取走数据...队列目前有" + blockingQueue.size() + "个数据");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
