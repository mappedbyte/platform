package com.francis.platform.common.annotation;

import java.lang.annotation.*;

/**
 * A unified response object is not returned
 * @author Francis
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {

}
