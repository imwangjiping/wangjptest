package com.jwong.junit.java8.optional;

import com.jwong.junit.java8.Lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * OptionalTest class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 *
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class OptionalTest {

    @Test
    public void test4() {
        // map(Function f)
        Optional<Employee> employeeOptional = Optional.ofNullable(new Employee(222, "jwong222"));
        employeeOptional.map((e) -> {
            e.setName(e.getName() + "333");
            return e;
        });
        System.out.println(employeeOptional.get());

        // flatMap(Function mapper)
        Optional<Employee> stringOptional = employeeOptional.flatMap((e) -> {
            e.setName(e.getName() + "444");
            return Optional.of(e);
        });
        System.out.println(stringOptional.get());

    }

    @Test
    public void test3() {
        // isPresent()
        Optional<Employee> employeeOptional = Optional.ofNullable(new Employee());
        if (employeeOptional.isPresent()) {
            System.out.println(employeeOptional.get());
        }

        // orElse(T t)
        Optional<Employee> employeeOptional1 = Optional.ofNullable(null);
        Employee employee = employeeOptional1.orElse(new Employee(222, "jwong222"));
        System.out.println(employee);

        // orElseGet(Supplier s)
        Optional<Employee> employeeOptional2 = Optional.ofNullable(null);
        Employee employee1 = employeeOptional2.orElseGet(() -> new Employee());
        System.out.println(employee1);

    }

    @Test
    public void test2() {
        Optional<Employee> employeeOptional = Optional.empty();
        System.out.println(employeeOptional.get());
    }

    @Test
    public void test1() {
        Optional<Employee> employeeOptional = Optional.of(new Employee());
        System.out.println(employeeOptional.get());
    }

}
