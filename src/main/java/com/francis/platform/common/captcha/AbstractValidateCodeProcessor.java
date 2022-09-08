package com.francis.platform.common.captcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author Francis
 * @Date 2022-09-02 17:45
 **/
public abstract  class AbstractValidateCodeProcessor  implements ValidateCodeProcessor{


    abstract  ValidateCode generate(ServletRequest request, ServletResponse response);





}
