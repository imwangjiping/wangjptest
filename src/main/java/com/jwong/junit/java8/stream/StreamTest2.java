package com.jwong.junit.java8.stream;

import com.jwong.junit.java8.Lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * StreamTest2 class
 * <p>
 * 一、 Stream 的三个操作步骤：
 * <p>
 * 1. 创建 Stream()
 * <p>
 * 2. 中间操作
 * <p>
 * 3. 终止操作（终端操作）
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class StreamTest2 {

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

    // 中间操作

    /**
     * 排序
     * <p>
     * sorted() -- 自然排序
     * <p>
     * sorted(Comparator com) -- 定制排序
     */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("ccc", "aaa", "ddd", "bbb", "eee");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("----------------------------------------");

        employee.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return -e1.getAge().compareTo(e2.getAge()); // 倒序
                    }
                }).forEach(System.out::println);
    }

    /**
     * 映射
     * <p>
     * map -- 接收 Lambda ，将元素转换成另外一种形式或提取信息。
     * 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * <p>
     * flatMap -- 接收一个函数作为参数，将流中的每一个值都换成另外一个流，然后把所有流连成一个流。
     */
    @Test
    public void test5() {

        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("----------------------------------------");

        employee.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("----------------------------------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(StreamTest2::filterCharacter);

        stream.forEach((s) -> s.forEach(System.out::println));

        System.out.println("----------------------------------------");

        Stream<Character> stream1 = list.stream()
                .flatMap(StreamTest2::filterCharacter);
        stream1.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }


    /**
     * 筛选 与 切片
     * filter -- 接受 Lambda，从流中排除某些元素
     * limit -- 截断流，使其元素不超过给定数量
     * skip(n) -- 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * distinct -- 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素。
     */

    @Test
    public void test4() {
        employee.stream()
                .filter((e) -> e.getSalary() > 6000)
                .distinct()
                .skip(2).forEach(System.out::println);

    }

    @Test
    public void test3() {
        employee.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 6000;
                })
                .limit(2).forEach(System.out::println);

    }

    /**
     * 内部迭代： 迭代操作由 Stream API 完成
     */
    @Test
    public void test1() {

        // 中间操作： 不会执行任何操作
        Stream<Employee> stream = employee.stream()
                .filter((e) -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 25;
                });

        // 终止操作：一次性执行全部内容，即 “惰性操作”
        stream.forEach(System.out::println);

    }

    /**
     * 外部迭代：
     */
    @Test
    public void test2() {

        Iterator<Employee> iterator = employee.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
