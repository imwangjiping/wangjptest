package com.jwong.junit.test;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Test20190112 class
 *
 * @author J.Wong
 * @date 2019/01/14
 */
public class Test20190112 {

    /**
     * 逗号分割字符串数组
     */
    @Test
    public void test1() {
        String[] arrays = new String[]{"1", "2", "3"};
        System.out.println(String.join(",", arrays));
    }

    /**
     *
     */
    @Test
    public void test2() {
        String[] arrays = new String[]{"1", "2", "3"};
    }

    @Test
    public void test3() {
        List<List<String>> sqlParams = new ArrayList<>();
        List<String> params = Arrays.asList("1", "-", "3", "4");

        long count = params.stream().filter("-"::equals).count();
        System.out.println("count = " + count);
        int forCount = Integer.valueOf((params.size() - count) + "");

        for (int i = forCount; i >= 0; i--) {
            List<String> temp = new ArrayList<>(params);
            sqlParams.add(temp);
            System.out.println(sqlParams);
            for (int j = params.size() - 1; j >= 0; j--) {
                if ("-".equals(params.get(j))) {
                    continue;
                } else {
                    params.set(j, "-");
                    break;
                }
            }
        }

        System.out.println(sqlParams);
    }

    @Test
    public void test4() {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("1");
        ids.add("2");
        ids.add("2");

        List<String> collect = ids.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test5() throws InterruptedException {
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(second);

        Thread.sleep(1000);
        Long s = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(s);
    }

    /**
     * 获取某个时间是当年的第几周
     */
    @Test
    public void test6() {
        LocalDateTime firstDayOfYear = LocalDateTime.of(2019, 1, 7, 0, 0);
        System.out.println(firstDayOfYear.getDayOfWeek().getValue());
        firstDayOfYear = firstDayOfYear.plusDays(4L);
        WeekFields week = WeekFields.of(Locale.getDefault());
        int startWeek = firstDayOfYear.get(week.weekOfWeekBasedYear());

        LocalDateTime today = LocalDateTime.of(2019, 1, 13, 0, 0);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int todayWeek = today.get(weekFields.weekOfWeekBasedYear());

        System.out.println(todayWeek - startWeek + 1);
    }

    @Test
    public void test7() {

        LocalDateTime localDateTime = LocalDateTime.of(2019, 5, 1, 0, 0);

        System.out.println(getQuarter(localDateTime));
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.withYear(LocalDateTime.now().getYear()).withMonth(LocalDateTime.now().getMonthValue()).withDayOfMonth(LocalDateTime.now().getDayOfMonth()));
    }

    private int getQuarter(LocalDateTime localDateTime) {
        int monthValue = localDateTime.getMonthValue();
        switch (monthValue) {
            case 3:
            case 4:
            case 5:
                return 1;
            case 6:
            case 7:
            case 8:
                return 2;
            case 9:
            case 10:
            case 11:
                return 3;
            default:
                return 4;
        }
    }

    @Test
    public void test8() {

        System.out.println(Instant.now());
        System.out.println(new Date().getTime());
    }

    @Test
    public void test9() {
        String[] split = "   1,     8asd,   s".split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        System.out.println(Arrays.toString(split));
        System.out.println(Arrays.stream(split).collect(Collectors.joining(",")));
    }

    @Test
    public void test10() {
        StringBuffer s = new StringBuffer("   ");
        System.out.println(s.toString().trim().length());
    }

    @Test
    public void test11() {
        // System.out.println(this.newVersion(""));
        for (int i = 0; i < 1000; i++) {
            System.out.println(new Random().nextInt(3) );
        }
    }

    public static String newVersion(String version) {
        version = "V1.99.99";
        String[] versionSplit = version.substring(1).split("\\.");
        int a = Integer.parseInt(versionSplit[0]);
        int b = Integer.parseInt(versionSplit[1]);
        int c = Integer.parseInt(versionSplit[2]);
        if (++c > 100) {
            c = 0;
            if (++b > 100) {
                b = 0;
                return "V" + (++a) + "." + b + "." + c;
            }
            return "V" + a + "." + b + "." + c;
        }
        return "V" + a + "." + b + "." + c;
    }

    @Test
    public void test12() {
        // System.out.println(this.newVersion(""));
        for (int i = 0; i < 1000; i++) {
            System.out.println(new Random().nextInt(3) );
        }
    }

}
