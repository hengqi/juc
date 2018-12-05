package com.chenhl.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * @Auther: chenhonglei
 * @Date: 2018/11/27 20:35
 * @Description:
 */
public class SerialExecutor implements Executor {

    // 任务队列
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();

    // 这个才是真正的执行器
    final Executor executor;


    // 当前正在执行的任务
    Runnable active;

    // 初始化的时候，指定执行器
    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    // 添加任务到线程池: 将任务添加到任务队列，scheduleNext 触发执行器去任务队列取任务
    @Override
    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });

        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            // 具体的执行转给真正的执行器 executor
            executor.execute(active);
        }
    }
}
