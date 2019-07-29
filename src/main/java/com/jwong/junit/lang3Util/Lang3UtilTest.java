package com.jwong.junit.lang3Util;

import com.jwong.junit.test.clazz.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Lang3UtilTest {

    @Test
    public void Test1() {
        Person person = new Person();
        person.setAge(20);
        person.setName("jwong");
        try {
            Map<String, String> map = BeanUtils.describe(person);
            map.remove("class");
            map.forEach((key, value) -> System.out.println("key:" + key + ", value:" + value));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
