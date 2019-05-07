package com.jwong.junit.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * StreamTest1 class
 * <p>
 * 一、 Stream 的三个操作步骤：
 * <p>
 * 1. 创建 Stream()
 * <p>
 * 2. 中间操作
 * <p>
 * 3. 终止操作（终端操作）
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class StreamTest1 {

    // 创建流
    @Test
    public void test1() {
        // 1. 通过 Collection 系列的集合提供的 stream() 或者 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2. 通过 Arrays 中的静态方法 stream() 获取数组流
        String[] str = new String[5];
        Stream<String> stream2 = Arrays.stream(str);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream3 = Stream.of("a", "b");

        // 4. 无限流
        // 4.1 迭代
        Stream<Integer> stream4 = Stream.iterate(1, (x) -> x + 1);

        stream4.limit(10)
                .forEach(System.out::println);

        // 4.2 生成
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);

    }
}
