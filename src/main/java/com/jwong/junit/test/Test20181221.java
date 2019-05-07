package com.jwong.junit.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test20181221 {
    public static void main(String[] args) {
        A a = new A();
        a.setNo("123");
        A b = new A();
        b.setNo("456");
        List<A> l = Arrays.asList(a, b);
        System.out.println(l);
        System.out.println(l.stream().map(A::getNo).collect(Collectors.toList()));
    }

    static class A {
        String no;
        Boolean check = false;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public Boolean getCheck() {
            return check;
        }

        public void setCheck(Boolean check) {
            this.check = check;
        }
    }
}
