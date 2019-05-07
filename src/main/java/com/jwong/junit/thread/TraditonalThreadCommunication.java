package com.jwong.junit.thread;

/**
 * @author jwong 2017年10月20日 16:25:27
 */

/**
 * 子线程循环90次, 主线程循环10次, 接着又回到子线程循环90次, 接着再回到主线程又循环10次, 如此循环50次.
 */
public class TraditonalThreadCommunication {
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

}

class SynchronizedThread {

    // 因为子线程先循环!!!
    boolean isMain = false;

    public synchronized void main(int i) {
        while (!isMain) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("main thread --> " + j + ", loop of --> " + i);
        }
        isMain = false;
        this.notify();
    }

    public synchronized void sub(int i) {
        while (isMain) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 90; j++) {
            System.out.println("sub thread --> " + j + ", loop of --> " + i);
        }
        isMain = true;
        this.notify();
    }
}
