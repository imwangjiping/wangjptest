package com.jwong.junit.java8.annotation;

/**
 * AnnotationTest class
 *
 * @author J.Wong
 * @date 2017/12/27
 */

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解 与 类型注解
 */
public class AnnotationTest {

    @Test
    public void test1() throws Exception {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        Method method = annotationTestClass.getMethod("show");
        MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("jjwong")
    public void show(@MyAnnotation("jjwong") String name) {

    }
}
