package com.jwong.junit.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jwong on 2017/10/19.
 */
public class TraditionalTimerTest {

    public static void main(String[] args) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bomb...");
            }
        }, 5000, 1000);
    }
}
