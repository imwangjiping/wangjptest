package com.jwong.junit.thread;

/**
 * @author jwong 2017年10月20日 09:13:09
 */
public class TraditionalThread {

    public static void main(String[] args) {

        /**
         * 第一个线程
         */
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(500);
                        System.out.println("1-1: " + Thread.currentThread().getName());
                        System.out.println("1-2: " + this.getName());
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();

        /**
         * 第二个线程
         */
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(500);
                        System.out.println("2-1: " + Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                }
            }
        });
        thread2.start();

        /**
         * 第三个线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(500);
                        System.out.println("3-1: " + Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                }
            }
        }) {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(500);
                        System.out.println("3-2: " + Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();

    }

}
