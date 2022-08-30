package com.francis.platform.config.schedule;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 *
 * @author Francis
 */
@Slf4j
public class Threads {
    private Threads(){}

    public static void sleep(long milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {
            return;
        }
    }

    /**
     * To stop the thread pool, use shutdown first,
     * stop receiving new tasks and try to complete all existing tasks. If it times out, call shutdownNow,
     * cancel the pending tasks in the workQueue, and interrupt all blocking functions.
     * If it still times out, force quit. In addition, the thread itself is interrupted by the call during shutdown.
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool)
    {
        log.info("\033[1;95mExecutor pool: [{}]\033[1;95m",pool);
        if (pool != null && !pool.isShutdown())
        {
            pool.shutdown();
            try
            {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS))
                {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS))
                    {
                        log.info("Pool did not terminate");
                    }
                }
            }
            catch (InterruptedException ie)
            {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }


    public static void printException(Runnable r, Throwable t)
    {
        if (t == null && r instanceof Future<?>)
        {
            try
            {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                {
                    future.get();
                }
            }
            catch (CancellationException ce)
            {
                t = ce;
            }
            catch (ExecutionException ee)
            {
                t = ee.getCause();
            }
            catch (InterruptedException ie)
            {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null)
        {
            log.error(t.getMessage(), t);
        }
    }
}
