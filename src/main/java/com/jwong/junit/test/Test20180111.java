package com.jwong.junit.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Test20180111 class
 *
 * @author J.Wong
 * @date 2018/01/11
 */
public class Test20180111 {
    @Test
    public void test1() {
        System.out.println(888.68 - 0.1);

        BigDecimal a = new BigDecimal(Double.toString(888.68));
        BigDecimal b = new BigDecimal(Double.toString(0.1));
        System.out.println(a.subtract(b).doubleValue());
    }

    @Test
    public void test2() throws Exception {
        ZonedDateTime  param= ZonedDateTime.now();
        ZonedDateTime now = ZonedDateTime.now().minusDays(1);
        ZonedDateTime dateTimeEight = ZonedDateTime.of(param.getYear(), param.getMonthValue(), param.getDayOfMonth(), 8, 0, 0, 0, param.getZone());
        System.out.println(now);
        System.out.println(dateTimeEight);
        if (now.isAfter(dateTimeEight)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
