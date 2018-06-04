package com.chenhl.juc.threadpool;

import java.util.concurrent.ExecutorService;

/*
    线程池的思想：

 */

public class ThreadPoolTest1 {

    public static void main(String[] args) {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor();
        executor.init();

        ExecutorService pool = executor.getCustomThreadPoolExecutor();

        for (int i = 1; i < 100; i++) {
            System.out.println("提交第" + i + "个任务");
            pool.execute(new MyRunnable());
        }


//        executor.destroy();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("running========" + Thread.currentThread().getName() + "==start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("running========" + Thread.currentThread().getName() + "==end");
    }
}
