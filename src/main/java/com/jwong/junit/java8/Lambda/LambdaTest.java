package com.jwong.junit.java8.Lambda;

import org.junit.Test;

import java.util.*;

/**
 * Created by jwong on 2017/10/26.
 */
public class LambdaTest {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void Test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);

    }


    /**
     * Lambda 表达式
     */
    @Test
    public void Test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employee = Arrays.asList(
            new Employee("张三", 18L, 9999.99),
            new Employee("李四", 38L, 5555.55),
            new Employee("王五", 50L, 6666.66),
            new Employee("赵六", 16L, 3333.33),
            new Employee("田七", 8L, 7777.77)
    );

    /**
     * 需求: 获取当前公司中员工年龄大于 35 岁的员工信息
     */
    /**
     * 优化方式1 ( 使用 设计模式, 策略设计模式 )
     */
    @Test
    public void Test3() {

        List<Employee> employeeList = filterEmployee(employee, new FilterEmployeeByAge());
        for (Employee e : employeeList) {
            System.out.println(e);
        }

        System.out.println("-------------------------------------------------");

        List<Employee> employeeList2 = filterEmployee(employee, new FilterEmployeeBySalary());
        for (Employee e : employeeList2) {
            System.out.println(e);
        }

    }


    /**
     * 优化方式2 ( 使用 匿名内部类 )
     */
    @Test
    public void Test4() {
        List<Employee> employeeList = filterEmployee(employee, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 35;
            }
        });
        for (Employee e : employeeList) {
            System.out.println(e);
        }

    }

    /**
     * 优化方式3 ( 使用 Lambda表达式 )
     */
    @Test
    public void Test5() {
        List<Employee> employeeList = filterEmployee(employee, (e) -> e.getAge() <= 35);
        employeeList.forEach(System.out::println);
    }

    /**
     * 优化方式4 ( 使用 Stream API )
     */
    @Test
    public void Test6() {
        employee.stream().filter((Employee e) -> e.getSalary() >= 5000).limit(2).forEach(System.out::println);

        System.out.println("-------------------------------------------------");

        employee.stream().map(Employee::getName).forEach(System.out::println);
    }

    public List<Employee> filterEmployee(List<Employee> mps, MyPredicate<Employee> mp) {
        List<Employee> list = new ArrayList<>();
        for (Employee e : mps) {
            if (mp.test(e)) {
                list.add(e);
            }
        }
        return list;
    }

}
