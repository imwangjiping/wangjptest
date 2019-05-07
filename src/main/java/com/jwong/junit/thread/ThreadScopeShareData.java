package com.jwong.junit.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author jwong 2017年10月20日 09:13:00
 */
public class ThreadScopeShareData {

    private static Map<Thread, Integer> dataMap = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data: " + data);
                    dataMap.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                    new C().get();
                }
            }).start();
        }
    }

    static class A {
        int data = dataMap.get(Thread.currentThread());

        public void get() {
            System.out.println("A from " + Thread.currentThread().getName() + " get data: " + data);
        }
    }

    static class B {
        int data = dataMap.get(Thread.currentThread());

        public void get() {
            System.out.println("B from " + Thread.currentThread().getName() + " get data: " + data);
        }
    }

    static class C {
        int data = dataMap.get(Thread.currentThread());

        public void get() {
            System.out.println("C from " + Thread.currentThread().getName() + " get data: " + data);
        }
    }

}
