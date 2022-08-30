package com.francis.platform.config.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @author Francis
 */
@Component
@Slf4j
public class ShutdownManager {

    private final ScheduledExecutorService scheduledExecutorService;

    public ShutdownManager(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            log.info("\033[32;1m" + "====关闭后台任务任务线程池====" + "\033[0m");
            shutdown();

        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }
    }


    public void shutdown() {

        Threads.shutdownAndAwaitTermination(scheduledExecutorService);
    }


}
