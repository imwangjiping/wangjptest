package com.jwong.junit.jdk;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ReflectClass class
 *
 * @author J.Wong
 * @date 2019/05/14
 */
public class ReflectClass {

    @Test
    public void Test1() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        // POJO 的权限类名
        String klass = "com.jwong.junit.test.clazz.Person";
        Class<?> pojo = Class.forName(klass);
        // 实例化对象
        Object obj = pojo.newInstance();
        // 得到 set 方法
        Method setMethod = pojo.getDeclaredMethod("setName", String.class);
        // 调用 POJO 的 set 方法
        setMethod.invoke(obj, "jwong");
        // 得到 get 方法
        Method getMethod = pojo.getDeclaredMethod("getName");
        // 调用 POJO 的 get 方法
        System.out.println(getMethod.invoke(obj));
    }
}
