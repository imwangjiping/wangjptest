package com.jwong.junit.java8.time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * SimpleDateFormatProblemTest class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class SimpleDateFormatProblemTest {

    @Test
    public void test1() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Callable<Date> call = () -> simpleDateFormat.parse("2017-12-26");

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<Date>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futureList.add(executorService.submit(call));
        }

        for (Future<Date> dateFuture : futureList) {
            System.out.println(dateFuture.get());
        }
        executorService.shutdown();

    }


}
