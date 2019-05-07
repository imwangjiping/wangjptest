package com.jwong.junit.test;

import com.jwong.junit.java8.Lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Test20171226 class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class Test20171226 {

    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    List<Employee> employee = Arrays.asList(
            new Employee("李四", 38L, 5555.55),
            new Employee("赵六", 16L, 3333.33),
            new Employee("王五", 50L, 6666.66),
            new Employee("张三", 18L, 9999.99),
            new Employee("田七", 8L, 7777.77),
            new Employee("田七", 8L, 7777.77),
            new Employee("田七", 8L, 7777.77),
            new Employee("田七", 8L, 7777.77)
    );

    @Test
    public void test2() {
        Optional<Integer> count = employee.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(count.get());
    }
}
