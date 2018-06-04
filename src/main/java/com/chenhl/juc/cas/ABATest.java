package com.chenhl.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        });

        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean bool = atomicInteger.compareAndSet(100, 101);
            System.out.println(bool);//true
        });

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedReference.compareAndSet(100, 101,
                        atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                atomicStampedReference.compareAndSet(101, 100,
                        atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            }
        });

        Thread t4 = new Thread(new Runnable() {
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println("before sleep: stamp=" + stamp);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after sleep: stamp=" + stamp);
                System.out.println("当前 stamp: " + atomicStampedReference.getStamp());
                boolean bool = atomicStampedReference.compareAndSet(100, 101,
                        stamp, stamp + 1);

                System.out.println(bool);//false
            }
        });

        t3.start();
        t4.start();
    }

}
