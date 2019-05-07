package com.jwong.junit.java8.defaultInterface;

/**
 * MyInterface class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public interface MyInterface {

    default String getName() {
        return "jwong_interface";
    }

    /**
     * 这里添加描述信息
     */
    public static String show() {
        return "jwong_interface_static_show()";
    }

}
