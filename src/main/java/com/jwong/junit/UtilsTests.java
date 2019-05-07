package com.jwong.junit;

import com.jwong.junit.utils.JWongStringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UtilsTests class
 *
 * @author J.Wong
 * @date 2017/12/20
 */
public class UtilsTests {

    @Test
    public void Test1() {

        System.out.println(JWongStringUtils.noString(null, null, "1"));

        System.out.println(JWongStringUtils.connectEverything(true, false, 123, 0.0, "j", "jwong").toString());

        List<String> list = new ArrayList<String>() {
            {
                add("qqq");
                add("www");
                add("eee");
                add("qqq");
                add("www");
                add("eee");
                add("rrr");
            }
        };

        List<Person> personList = new ArrayList<Person>() {
            {
                add(new Person("刘一", 1));
                add(new Person("陈二", 2));
                add(new Person("张三", 3));
                add(new Person("李四", 4));
                add(new Person("王五", 5));
                add(new Person("赵六", 6));
                add(new Person("孙七", 7));
                add(new Person("周八", 8));
                add(new Person("吴九", 9));
                add(new Person("郑十", 10));
            }
        };
        // JWongStringUtils.List2Set(list).forEach(System.out::println);

        Set<String> stringSet = new HashSet<>();
        personList.forEach(System.out::println);

    }

    class Person {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

}
