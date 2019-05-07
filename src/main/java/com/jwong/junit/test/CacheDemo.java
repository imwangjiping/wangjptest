package com.jwong.junit.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存系统 -- 伪代码
 *
 * @author jwong 2017年10月23日 15:32:54
 */
public class CacheDemo {

    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {

    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        rwl.readLock().lock();  // 开启读锁
        Object value = null;
        try {
            value = cache.get(key);
            // 缓存里没有值
            if (value == null) {
                rwl.readLock().unlock();    // 释放读锁
                rwl.writeLock().lock(); // 开启写锁

                try {

                    /**
                     * 这里在此判断, 原因就是
                     * 上面释放读锁, 或有多个线程进行读取, 如果同时3个线程, 那么3个线程的value都为null, 这个时候, 写数据就会被执行3次.
                     */
                    if (cache.get(key) == null) {
                        value = "jwong";  // 写入数据!!!! 伪代码
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rwl.writeLock().unlock();// 释放写锁
                }

                rwl.readLock().lock();  // 开启读锁
            }
        } finally {
            rwl.readLock().unlock();    // 释放读锁
        }

        return null;
    }

}
