package com.jwong.junit.thread;

import java.util.Random;

/**
 * @author jwong 2017年10月20日 09:12:39
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<MyThreadScopeData>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data: " + data);
                    threadLocal.set(data);

                    MyThreadScopeData.getThreadInstance().setName("name" + data);
                    MyThreadScopeData.getThreadInstance().setAge(data);

                    new A().get();
                    new B().get();
                    new C().get();
                }
            }).start();
        }
    }

    static class A {
        int data = threadLocal.get();

        public void get() {
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " get myData: " + myData.getName() + "," + myData.getAge());
        }
    }

    static class B {
        int data = threadLocal.get();

        public void get() {
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " get myData: " + myData.getName() + "," + myData.getAge());
        }
    }

    static class C {
        int data = threadLocal.get();

        public void get() {
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("C from " + Thread.currentThread().getName() + " get myData: " + myData.getName() + "," + myData.getAge());
        }
    }
}

class MyThreadScopeData {

    private MyThreadScopeData() {
    }

    public static MyThreadScopeData getThreadInstance() {
        MyThreadScopeData threadInstance = map.get();
        if (threadInstance == null) {
            threadInstance = new MyThreadScopeData();
            map.set(threadInstance);
        }
        return threadInstance;
    }

    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    /******************************* 成员变量 *******************************/

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
