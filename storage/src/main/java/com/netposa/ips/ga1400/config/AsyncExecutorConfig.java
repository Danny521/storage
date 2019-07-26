package com.netposa.ips.ga1400.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * Author: zhangtao@netposa.com on 2019/3/7
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncExecutorConfig implements AsyncConfigurer {

    @Value("${task.threadpool.core}")
    private int core;
    @Value("${task.threadpool.max-size}")
    private int max;

    @Bean(name = "executor")
    @Override
    public Executor getAsyncExecutor() {
        log.info("------>初始化异步线程池配置");
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(core);//当前(最小)线程数
        threadPool.setMaxPoolSize(max);// 最大线程数
        threadPool.setQueueCapacity(100);//线程池所使用的缓冲队列大小
        threadPool.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setAwaitTerminationSeconds(1000 * 60);// 等待时间 （默认为0，此时立即停止），并没等待60秒钟后强制停止
        threadPool.setThreadNamePrefix("async-");//  线程名称前缀
        threadPool.initialize(); // 初始化
        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new DetectAsyncExceptionHandler();
    }

    /**
     * 自定义异常处理类
     */
    class DetectAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        //手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            int index = RandomUtils.nextInt(0, 1000);
            log.error("------------->>>>>>>>[{}]捕获线程异常信息", index);
            log.error("------->[{}]Exception Message={}", index, throwable.getMessage());
            log.error("------->[{}]Exception Method name={}", index, method.getName());
            for (Object param : obj) {
                log.error("------->[{}]Exception Method Parameter value={}", index, param);
            }
            log.error("------->[{}]Exception Throwable={}", index, throwable);
        }
    }


}
