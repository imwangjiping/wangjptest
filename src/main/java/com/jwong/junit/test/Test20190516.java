package com.jwong.junit.test;

import com.jwong.junit.test.clazz.TestDTO;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Test20190112 class
 *
 * @author J.Wong
 * @date 2019/01/14
 */
public class Test20190516 {

    /**
     * 逗号分割字符串数组
     */
    @Test
    public void test1() {
        System.out.println(String.format("%05d", 123));
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        System.out.println(val);
        System.out.println(27.0 / 2 + 48.0 / 3);

    }

    @Test
    public void test2() throws URISyntaxException {
        String url = "https://snv.iteye.com/blog/1995604";
        URI uri = new URI(url);
        System.out.println(1);

        System.out.println(LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Test
    public void test3() {
        List<TestDTO> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestDTO each = new TestDTO();
            each.setUser("user " + (i + 1));
            each.setDesc("desc " + (i + 1));
            result.add(each);
        }
        Map<String, String> map = new HashMap<>();
        convert(result, TestDTO::getUser, TestDTO::setUser);

    }

    public static <T> void convert(List<T> list, Function<T, String> function, BiConsumer<T, String> consumer) {
        List<String> collect = list.stream().map(function).collect(Collectors.toList());
        Map<String, String> map = new HashMap<String, String>() {{
            put("user 1", "U1");
        }};
        System.out.println(collect);
        list.forEach(l -> {
            consumer.accept((T) l, map.get(function.apply(l)));
        });
        System.out.println(list);
    }

}
