package com.jwong.junit.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程顺序执行
 * Created by jwong on 2017/10/23.
 */
public class ThreeConditionCommunicationTest {
    public static void main(String[] args) {

        final ThreadCondition threadCondition = new ThreadCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    threadCondition.thread1(i);
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    threadCondition.thread2(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    threadCondition.thread3(i);
                }
            }
        }).start();

    }

    static class ThreadCondition {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        private boolean isThread1 = true;
        private boolean isThread2 = false;
        private boolean isThread3 = false;

        private void thread1(int i) {
            lock.lock();
            try {
                while (!isThread1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("thread 1 --> " + i);

                isThread1 = false;
                isThread2 = true;
                isThread3 = false;

                condition2.signal();
            } finally {
                lock.unlock();
            }

        }

        private void thread2(int i) {
            lock.lock();
            try {
                while (!isThread2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("thread 2 --> " + i);

                isThread1 = false;
                isThread2 = false;
                isThread3 = true;

                condition3.signal();
            } finally {
                lock.unlock();
            }

        }

        private void thread3(int i) {
            lock.lock();

            try {
                while (!isThread3) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("thread 3 --> " + i);

                isThread1 = true;
                isThread2 = false;
                isThread3 = false;

                condition1.signal();

            } finally {
                lock.unlock();
            }
        }
    }
}

