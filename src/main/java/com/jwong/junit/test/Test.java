package com.jwong.junit.test;

/**
 * Created by jwong on 2017/10/24.
 */
public class Test {
    public static void main(String[] args) {
        String string = "";
        StringBuffer stringBuffer = new StringBuffer();

        System.out.println("String begin: " + System.currentTimeMillis() / 1000);

        for (int i = 0; i < 10000; i++) {
            string += "1";
        }

        System.out.println("String end: " + System.currentTimeMillis() / 1000);


        System.out.println("StringBuffer begin: " + System.currentTimeMillis() / 1000);

        for (int i = 0; i < 10000; i++) {
            stringBuffer.append("1");
        }

        System.out.println("StringBuffer end: " + System.currentTimeMillis() / 1000);

        StringBuffer sb = new StringBuffer("pjgnaw");
        System.out.println(sb.reverse());


    }
}
