package com.jwong.junit.java8.defaultInterface;

import org.junit.Test;

/**
 * DefaultInterfaceTest class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class DefaultInterfaceTest {

    @Test
    public void test1() {
        // 类优先原则
        MySubClass mySubClass = new MySubClass();
        System.out.println(mySubClass.getName());
        System.out.println(MyInterface.show());
    }

}
