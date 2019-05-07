package com.jwong.junit.java8.stream;

import com.jwong.junit.java8.Lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * StreamTest3 class
 * <p>
 * 一、 Stream 的三个操作步骤：
 * <p>
 * 1. 创建 Stream()
 * <p>
 * 2. 中间操作
 * <p>
 * 3. 终止操作（终端操作） 当前class所在步骤
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class StreamTest3 {

    List<Employee> employee = Arrays.asList(
            new Employee("李四", 38L, 5555.55, Employee.Status.BUSY),
            new Employee("赵六", 16L, 3333.33, Employee.Status.FREE),
            new Employee("王五", 50L, 6666.66, Employee.Status.VOCATION),
            new Employee("张三", 18L, 9999.99, Employee.Status.FREE),
            new Employee("田七", 8L, 7778.77, Employee.Status.BUSY),
            new Employee("田七", 8L, 7778.77, Employee.Status.BUSY)
    );

    // 终止操作（终端操作）

    /**
     * 搜集
     * collect -- 将流转换为其他形式。接收一个 Collector 接口的实现，用于给 Stream 中元素做汇总的方法
     */
    @Test
    public void test10() {
        String str = employee.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.joining(",", "---", "---"));
        System.out.println(str);
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics dss = employee.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
    }

    // 分区
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employee.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(map);
    }

    // 多级分组
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> map = employee.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() < 20) {
                        return "青年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }

    // 分组
    @Test
    public void test6() {
        Map<Employee.Status, List<Employee>> map = employee.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    @Test
    public void test5() {

        // 总数
        Long count = employee.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("------------------------------");

        // 平均值
        Double avg = employee.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        System.out.println("------------------------------");

        // 总和
        Double sum = employee.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        System.out.println("------------------------------");

        // 最大值
        Optional<Employee> max = employee.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        System.out.println("------------------------------");

        Optional<Double> min = employee.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());

    }

    @Test
    public void test4() {
        List<String> nameList = employee.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        nameList.forEach(System.out::println);

        System.out.println("------------------------------");

        employee.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("------------------------------");

        employee.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);

    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("-----------------------------------------");

        Optional<Double> doubleOptional = employee.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(doubleOptional.get());
    }

    /**
     * 查找 与 匹配
     * allMatch -- 检查是否匹配所有元素
     * anyMatch -- 检查是否至少匹配一个元素
     * noneMatch -- 检查是否没有匹配所有元素
     * findFirst -- 返回第一个元素
     * findAny -- 返回当前流中任意元素
     * count -- 返回流中元素个数
     * max -- 返回流中最大值
     * min -- 返回流中最小值
     */
    @Test
    public void test2() {
        long count = employee.stream().count();
        System.out.println(count);

        Optional<Employee> employeeOptional = employee.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(employeeOptional.get());

        Optional<Employee> employeeOptional1 = employee.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(employeeOptional1.get());

        Optional<Double> employeeOptional2 = employee.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(employeeOptional2.get());
    }

    @Test
    public void test1() {

        boolean b = employee.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        boolean b1 = employee.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employee.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        Optional<Employee> employeeOptional = employee.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(employeeOptional.get());

        Optional<Employee> employeeOptional1 = employee.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(employeeOptional1.get());

    }

}
