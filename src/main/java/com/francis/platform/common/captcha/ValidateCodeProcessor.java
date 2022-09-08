package com.francis.platform.common.captcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author Francis
 * @Date 2022-09-02 17:39
 **/
public interface ValidateCodeProcessor {

    /**
     *  Create Code
     * @param request
     * @param response
     */
    void create(ServletRequest request, ServletResponse response);


    /**
     * Validate Code
     * @param request
     * @param response
     */
    void validate(ServletRequest request, ServletResponse response);


}
