package com.jwong.junit.thread;

/**
 * @author jwong 2017年10月20日 16:25:27
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 子线程循环90次, 主线程循环10次, 接着又回到子线程循环90次, 接着再回到主线程又循环10次, 如此循环50次.
 */
public class ConditionCommunication {
    public static void main(String[] args) {

        final SynchronizedThread synchronizedThread = new SynchronizedThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    synchronizedThread.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            synchronizedThread.main(i);
        }
    }

    static class SynchronizedThread {

        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        // 因为子线程先循环!!!
        boolean isMain = false;

        public void main(int i) {
            lock.lock();
            try {
                while (!isMain) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("main thread --> " + j + ", loop of --> " + i);
                }
                isMain = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub(int i) {
            lock.lock();
            try {
                while (isMain) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 90; j++) {
                    System.out.println("sub thread --> " + j + ", loop of --> " + i);
                }
                isMain = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

}


