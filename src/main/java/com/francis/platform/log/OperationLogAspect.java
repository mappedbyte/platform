package com.francis.platform.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author Francis
 * @Date 2022-09-05 17:21
 **/
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    ThreadLocal<Long> currentTime = new ThreadLocal<>();


    @Pointcut("@annotation(OperationLog)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();


        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {

        currentTime.remove();

    }

    public String getUsername() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            return "";
        }
    }


}
