package com.jwong.junit.thread;

/**
 * Created by jwong on 2017/10/24.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用 队列阻塞 方式!!!
 * 子线程循环9次, 主线程循环5次, 接着又回到子线程循环9次, 接着再回到主线程又循环5次, 如此循环9次.
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {
        final putOut putOut = new putOut();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 9; i++) {
                    putOut.main(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 9; i++) {
                    putOut.sub(i);
                }
            }
        }).start();

    }

    static class putOut {
        BlockingQueue<Integer> blockingQueue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> blockingQueue2 = new ArrayBlockingQueue<Integer>(1);

        {
            try {
                blockingQueue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(int i) {
            try {
                blockingQueue1.put(1);
                for (int j = 1; j <= 5; j++) {
                    System.out.println("main thread of " + j + ", loop of " + i);
                }
                blockingQueue2.take();
            } catch (Exception e) {
            }
        }

        public void sub(int i) {
            try {
                blockingQueue2.put(1);
                for (int j = 1; j <= 9; j++) {
                    System.out.println("sub thread of " + j + ", loop of " + i);
                }
                blockingQueue1.take();
            } catch (Exception e) {
            }
        }
    }

}
