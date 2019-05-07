package com.jwong.junit.java8.Lambda2;

import com.jwong.junit.java8.Lambda.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * MethodRefTest class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 */
public class MethodRefTest {

    // 数组引用
    @Test
    public void test8() {
        Function<Integer, String[]> function = (x) -> new String[x];
        System.out.println(function.apply(10).length);

        Function<Integer, String[]> function1 = String[]::new;
        System.out.println(function1.apply(9).length);
    }

    // 构造器引用
    @Test
    public void test5() {
        Supplier<Employee> supplier = () -> new Employee();
        System.out.println(supplier.get());

        // 构造器引用的方式
        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    @Test
    public void test6() {
        Function<Integer, Employee> function = (x) -> new Employee(x);
        System.out.println(function.apply(20));

        // 构造器引用的方式
        Function<Integer, Employee> function1 = Employee::new;
        System.out.println(function1.apply(21));
    }

    @Test
    public void test7() {
        BiFunction<Integer, String, Employee> function = (x, y) -> new Employee(x, y);
        System.out.println(function.apply(20, "jwong20"));

        // 构造器引用的方式
        BiFunction<Integer, String, Employee> function1 = Employee::new;
        System.out.println(function1.apply(21, "jwong21"));
    }

    // 类名 :: 实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("jwong", "jwong"));
    }

    // 类名 :: 静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comparator1 = Integer::compare;
    }

    // 对象的引用 :: 实例方法名
    @Test
    public void test1() {
        Consumer<String> consumer = (x) -> System.out.println(x);

        System.out.println("------------------------------");

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("123123123123");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());
        ;

        System.out.println("------------------------------");

        Supplier<String> supplier1 = employee::getName;
    }

}
