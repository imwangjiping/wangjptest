package com.jwong.junit.java8.Lambda2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * LambdaTest2 class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class LambdaTest2 {
    /**
     * Predicate<T> : 断言型接口
     * boolean test(T t);
     */
    @Test
    public void test4() {
        sslist(Arrays.asList("a","bb","ccc","dddd","eeeee","ffffff"), (x) -> x.length() > 3).forEach(System.out::println);
    }

    // 将满足条件的字符串放入集合中
    public List<String> sslist(List<String> stringList, Predicate<String> predicate) {
        List<String> list = new ArrayList<>();

        for(String str: stringList) {
            if (predicate.test(str)) {
                list.add(str);
            }
        }

        return list;
    }

    /**
     * Function<T, R> : 函数型接口
     * R apply(T t);
     */
    @Test
    public void test3() {
        String str = concat("           jwong", (s) -> s.trim());
        System.out.println(str);
    }

    // 用于字符串处理
    public String concat(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * Supplier<T> : 供给型接口
     * T get();
     */
    @Test
    public void test2() {
        List<Integer> integerList = supList(10, () -> (int) (Math.random() * 1000));
        for (Integer i : integerList) {
            System.out.println(i);
        }
    }

    // 产生指定个数的整数 放入集合中
    public List<Integer> supList(int count, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Consumer<T> : 消费型接口
     * void accept(T t);
     */
    @Test
    public void test1() {
        trip(10, (x) -> System.out.println("旅游用掉：" + x));
    }

    public void trip(double money, Consumer<Double> con) {
        con.accept(money);
    }

}
