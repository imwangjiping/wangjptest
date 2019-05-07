package com.jwong.junit.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程间数据交换
 * 这里开了4个线程, 只要前两个就可以了, 后面2个是测试用的.
 * Created by jwong on 2017/10/24.
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger exchanger = new Exchanger();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data = "data1";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data + "交换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    String exchange = (String) exchanger.exchange(data);

                    System.out.println("线程" + Thread.currentThread().getName() + "换回来的数据" + exchange);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data = "data2";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data + "交换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    String exchange = (String) exchanger.exchange(data);

                    System.out.println("线程" + Thread.currentThread().getName() + "换回来的数据" + exchange);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data = "data3";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data + "交换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    String exchange = (String) exchanger.exchange(data);

                    System.out.println("线程" + Thread.currentThread().getName() + "换回来的数据" + exchange);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data = "data4";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data + "交换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    String exchange = (String) exchanger.exchange(data);

                    System.out.println("线程" + Thread.currentThread().getName() + "换回来的数据" + exchange);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        executorService.shutdown();

    }

}