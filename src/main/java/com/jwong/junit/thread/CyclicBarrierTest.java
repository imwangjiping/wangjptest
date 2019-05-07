package com.jwong.junit.thread;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环阻塞, 路障, 减速带, 篱栅
 * Created by jwong on 2017/10/24.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final int count = 5;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 1; i <= count; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点1, 当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达, " + (cyclicBarrier.getNumberWaiting() == (count - 1) ? "都到齐了, 准备出发" : "正在等候") + "...");
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点2, 当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达, " + (cyclicBarrier.getNumberWaiting() == (count - 1) ? "都到齐了, 准备出发" : "正在等候") + "...");
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点3, 当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达, " + (cyclicBarrier.getNumberWaiting() == (count - 1) ? "都到齐了, 准备出发" : "正在等候") + "...");
                        cyclicBarrier.await();

                    } catch (Exception e) {
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

    }
}
