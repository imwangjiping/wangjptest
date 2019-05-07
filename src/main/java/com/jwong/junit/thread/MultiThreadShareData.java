package com.jwong.junit.thread;

/**
 * @author jwong 2017年10月20日 10:22:24
 */
public class MultiThreadShareData {

    private int j = 0;

    public static void main(String[] args) {
        // final ShareData1 data1 = new ShareData1();
        // new Thread(data1).start();
        // new Thread(data1).start();

        /*****************************************************/

        final ShareData2 data2 = new ShareData2();

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data2.decrement();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    data2.increment();
                }
            }).start();
        }

    }
}

/**
 * 卖票系统模拟
 * <p>
 * PS: 线程执行代码一致, 放入同一个Runnable对象中...
 *
 * @author jwong 2017年10月20日 10:33:59
 */
class ShareData1 implements Runnable {

    private int count = 100;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " --> " + --count);
            }
        }
    }
}

/**
 * PS: 线程代码执行不同的处理方式
 *
 * @author jwong 2017年10月20日 10:35:17
 */
class ShareData2 {
    private int j;

    public synchronized void inc() {
        System.out.println(Thread.currentThread().getName() + " --> " + ++j);
    }

    public synchronized void dec() {
        System.out.println(Thread.currentThread().getName() + " --> " + --j);
    }

    public void increment() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            inc();
        }
    }

    public void decrement() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            dec();
        }
    }
}