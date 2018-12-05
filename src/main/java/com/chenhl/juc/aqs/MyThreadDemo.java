package com.chenhl.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: chenhonglei
 * @Date: 2018/10/6 19:19
 * @Description:
 */
public class MyThreadDemo {

    private ReentrantLock lock = new ReentrantLock();

    public void test() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

}
