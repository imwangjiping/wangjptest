package com.jwong.junit.java8.annotation;

import java.lang.annotation.*;

/**
 * MyAnnotation class
 *
 * @author J.Wong
 * @date 2017/12/27
 */
@Repeatable(MyAnnotations.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "jwong";
}
