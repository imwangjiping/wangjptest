package com.jwong.junit.java8.stream;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinCalc class
 *
 * @author J.Wong
 * @date 2017/12/26
 */
public class ForkJoinCalc extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000L; //临界值

    public ForkJoinCalc(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            long middle = (start + end) / 2;

            ForkJoinCalc left = new ForkJoinCalc(start, middle);
            left.fork(); //拆分，并将该子任务压入线程队列

            ForkJoinCalc right = new ForkJoinCalc(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }

    }

}
