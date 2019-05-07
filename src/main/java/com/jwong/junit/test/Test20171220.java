package com.jwong.junit.test;

import java.util.function.Function;

/**
 * Test20171220 class
 *
 * @author J.Wong
 * @date 2017/12/20
 */
public class Test20171220 {

    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("123"));
    }

}
