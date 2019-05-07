package com.jwong.junit.java8.Lambda;

/**
 * Created by jwong on 2017/10/26.
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
