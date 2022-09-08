package com.francis.platform.log;

import java.lang.annotation.*;

/**
 * @Author Francis
 * @Date 2022-09-05 16:41
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    String value() default "";
}
