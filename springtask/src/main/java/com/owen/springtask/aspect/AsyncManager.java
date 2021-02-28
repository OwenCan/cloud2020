package com.owen.springtask.aspect;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 12:16
 **/
public class AsyncManager
{
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;


    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = null;

    /**
     * 单例模式
     */
    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me()
    {
        return me;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task)
    {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}