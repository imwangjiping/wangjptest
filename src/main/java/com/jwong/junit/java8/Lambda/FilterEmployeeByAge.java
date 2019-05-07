package com.jwong.junit.java8.Lambda;

/**
 * Created by jwong on 2017/10/26.
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
