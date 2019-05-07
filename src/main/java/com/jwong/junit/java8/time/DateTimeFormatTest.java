package com.jwong.junit.java8.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * DateTimeFormatTest class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class DateTimeFormatTest {

    @Test
    public void test1() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Callable<LocalDate> call = () -> LocalDate.parse("2017-12-26", dateTimeFormatter);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futureList.add(executorService.submit(call));
        }

        for (Future<LocalDate> dateFuture : futureList) {
            System.out.println(dateFuture.get());
        }
        executorService.shutdown();

    }


}
