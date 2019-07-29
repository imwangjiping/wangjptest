package com.jwong.junit.test;

/**
 * ConvertInterface class
 *
 * @author J.Wong
 * @date 2019/07/29
 */
public interface ConvertInterface<T, R> {

    R convert(T t);

}
