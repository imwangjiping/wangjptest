package com.jwong.junit.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 模拟多人购票系统
 *
 * @author jwong 2017年10月23日 10:26:38
 */
public class SaleTicketTest {
    final static int fori = 9;

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        List<Ticket> ticketList = new ArrayList<Ticket>();
        for (int i = 1; i <= fori; i++) {
            personList.add(new Person("王吉平" + i, 19 + i));
        }
        for (int i = 1; i <= fori; i++) {
            ticketList.add(new Ticket("上海" + i, "昆山" + i, 50));
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int index = new Random().nextInt(fori);
                    decTicket(personList, ticketList, index);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int index = new Random().nextInt(fori);
                    decTicket(personList, ticketList, index);
                }
            }
        }).start();

    }

    private synchronized static void decTicket(List<Person> personList, List<Ticket> ticketList, int index) {
        Ticket ticket = ticketList.get(index);
        int count = ticket.getCount();
        if (count > 0) {
            ticket.setCount(--count);
            System.out.println(Thread.currentThread().getName().replaceAll("-", "") + personList.get(index).getName() + " buy " + " [" + ticketList.get(index).getStartStation() + "] to [" + ticketList.get(index).getEndStation() + "] " + ", the count of " + ticketList.get(index).getCount());
        } else {
            System.out.println(Thread.currentThread().getName().replaceAll("-", "") + personList.get(index).getName() + " buy " + " [" + ticketList.get(index).getStartStation() + "] to [" + ticketList.get(index).getEndStation() + "] " + ", sold...");
        }
    }

    ;

}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

class Ticket {
    private String startStation;
    private String endStation;
    private int count;

    public Ticket(String startStation, String endStation, int count) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.count = count;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
