package com.jwong.junit.java8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * LocalDateTimeTest class
 *
 * @author J.Wong
 * @date 2017/12/27
 */
public class LocalDateTimeTest {

    // ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test8() {
        LocalDateTime localDateTime1 = LocalDateTime.now(ZoneId.of("Pacific/Bougainville"));
        System.out.println(localDateTime1);

        System.out.println("----------------------------------------");

        LocalDateTime localDateTime2 = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = localDateTime2.atZone(ZoneId.of("Pacific/Bougainville"));
        System.out.println(zonedDateTime1);

    }

    @Test
    public void test7() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    // DateTimeFormatter：格式化时间 / 日期
    @Test
    public void test6() {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1.format(dateTimeFormatter1));

        System.out.println("---------------------------------------");

        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime2 = LocalDateTime.now();
        String format2 = dateTimeFormatter2.format(localDateTime2);
        System.out.println(format2);

        System.out.println("---------------------------------------");

        LocalDateTime localDateTime3 = LocalDateTime.parse(format2, dateTimeFormatter2);
        System.out.println(localDateTime3);
    }

    // TemporalAdjuster
    @Test
    public void test5() {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        System.out.println(localDateTime1.withDayOfMonth(10));

        LocalDateTime localDateTime2 = localDateTime1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(localDateTime2);

        // 自定义下一个工作日
        LocalDateTime localDateTime4 = localDateTime1.with((x) -> {
            LocalDateTime localDateTime3 = (LocalDateTime) x;
            DayOfWeek dayOfWeek = localDateTime3.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDateTime3.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return localDateTime3.plusDays(2);
            } else {
                return localDateTime3.plusDays(1);
            }
        });
        System.out.println(localDateTime4);
    }

    // 3.
    // Duration：计算两个 “时间” 之间的间隔
    // Period：计算两个 “日期” 之间的间隔
    @Test
    public void test4() {
        LocalDate localDate1 = LocalDate.of(2017, 1, 1);
        LocalDate localDate2 = LocalDate.now();

        Period period = Period.between(localDate1, localDate2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    @Test
    public void test3() throws Exception {

        Instant instant1 = Instant.now();
        Thread.sleep(1000);
        Instant instant2 = Instant.now();

        System.out.println(Duration.between(instant1, instant2).toMillis());

        System.out.println("---------------------------------------");

    }

    // 2.Instant： 时间戳（以 UNINX 元年：1970年1月1日 00:00:00 到某个时间之间的毫秒值）
    @Test
    public void test2() {
        Instant instant = Instant.now();    // 默认获取 UTC 时区
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println(instant.toEpochMilli());

        Instant instant1 = Instant.ofEpochSecond(1);
        System.out.println(instant1);
    }

    // 1. LocalDate LocalTime LocalDateTime
    @Test
    public void test1() {

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        System.out.println("--------------------------------");

        LocalDateTime ldt2 = LocalDateTime.of(2017, 12, 1, 0, 0, 0, 0);
        System.out.println(ldt2);

    }

}
