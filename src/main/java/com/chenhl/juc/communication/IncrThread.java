package com.chenhl.juc.communication;

/**
 * Created by user on 2017/6/24.
 */
public class IncrThread extends Thread {

    private Sample sample;

    public IncrThread(Sample sample) {
        this.sample = sample;
    }

    @Override
    public void run() {
        for (int i =0; i<50; i++) {
            try {
                Thread.sleep((long)Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sample.incr();
        }
    }
}
