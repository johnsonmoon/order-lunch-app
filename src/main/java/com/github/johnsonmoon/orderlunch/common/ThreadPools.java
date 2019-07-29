package com.github.johnsonmoon.orderlunch.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by xuyh at 2019/7/29 20:47.
 */
public class ThreadPools {
    /**
     * 应用启动任务线程池
     */
    public static final ExecutorService applicationInitializeThreadPool = Executors.newFixedThreadPool(1);
}
