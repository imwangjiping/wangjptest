package com.jwong.junit.test;

import com.jwong.junit.test.clazz.Level;
import org.junit.Test;

/**
 * Test20171222 class
 *
 * @author J.Wong
 * @date 2017/12/22
 */
public class Test20171222 {

    /**
     * 枚举测试
     */
    @Test
    public void test1() {
        System.out.println(Level.A.ordinal());
        System.out.println(Level.B.ordinal());
        System.out.println(Level.C.ordinal());
        System.out.println(Level.D.ordinal());


        System.out.println(Level.A.getRange());
        System.out.println(Level.A.getText());

        System.out.println(Level.A.name());
        System.out.println("----------------------");

        System.out.println(Level.A.toString());
        System.out.println(Level.B.toString());
        System.out.println(Level.C.toString());
        System.out.println(Level.D.toString());
        System.out.println("----------------------");

        System.out.println(Level.A);
        System.out.println(Level.B);
        System.out.println(Level.C);
        System.out.println(Level.D);

    }

}
