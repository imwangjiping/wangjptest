package com.jwong.junit.java8.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * ForkJoinTest class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class ForkJoinTest {
    @Test
    public void test1() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalc(0L, 10000000000L);

        long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费的时间为: " + Duration.between(start, end).toMillis());
    }

    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum = 0L;

        for (long i = 0L; i <= 10000000000L; i++) {
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费的时间为: " + Duration.between(start, end).toMillis());
    }

    // 并行流
    @Test
    public void test3() {
        Instant start = Instant.now();

        LongStream.rangeClosed(0, 10000000000L)
                .parallel() // 并行流，底层是 ForkJoin
                // .sequential()   // 串行流
                .reduce(0, Long::sum);

        Instant end = Instant.now();
        System.out.println("耗费的时间为: " + Duration.between(start, end).toMillis());
    }

}
