package com.jwong.junit.test;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TestLocalDateTime class
 *
 * @author J.Wong
 * @date 2018/04/24
 */
public class TestLocalDateTime {

    @Test
    public void test1() {
        LocalDateTime local = LocalDateTime.now();
        local = LocalDateTime.of(local.getYear(), 4, 1, 14, 01);

        LocalDateTime localDateTime = local;
        localDateTime = local.plusDays(6L);
        localDateTime = local.plusMonths(1L).minusDays(1L);
        localDateTime = local.plusYears(1L).minusDays(1);
        System.out.println(LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), 23, 59, 59, 999999999));
    }


    @Test
    public void test2() {
        List<String> list = new ArrayList() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
        }};

        for (int i = list.size() - 1; i >= 0; i--) {
            if ("2".equals(list.get(i))) {
                for (int i1 = list.size() - 1; i1 >= 0; i1--) {
                    if ("1".equals(list.get(i1))) {
                        list.remove(i);
                        list.remove(i1);
                    }
                }
            }
        }

        System.out.println(list.toString());

    }
}
