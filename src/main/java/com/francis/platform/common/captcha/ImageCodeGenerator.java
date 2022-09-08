package com.francis.platform.common.captcha;

import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.common.exception.ExceptionCatch;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.util.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author Francis
 * @Date 2022-09-02 17:48
 **/

public class ImageCodeGenerator extends AbstractValidateCodeProcessor {

    public static ImageCodeGenerator me = new ImageCodeGenerator();

    private ImageCodeGenerator(){

    }

    @Override
    public void create(ServletRequest request, ServletResponse response) {
        ValidateCode generate = generate(request, response);
        RedisUtils.hset(AppConstants.REDIS_CAPTCHA_KEY, generate.validateKey, generate.generateResult, 120);
    }

    @Override
    public void validate(ServletRequest request, ServletResponse response) {
        String validateCodeResult = request.getParameter(AppConstants.RESPONSE_CAPTCHA_KEY);
        String uuid = request.getParameter(AppConstants.UUID);
        if (!RedisUtils.hasKey(AppConstants.REDIS_CAPTCHA_KEY, uuid)) {
            ExceptionCatch.castSecurity(CommonCode.CAPTCHA_EXPIRE);
        }
        if (!RedisUtils.hget(AppConstants.REDIS_CAPTCHA_KEY, uuid).equals(validateCodeResult)) {
            ExceptionCatch.castSecurity(CommonCode.CAPTCHA_ERROR);
        }
        RedisUtils.hset(AppConstants.REDIS_CAPTCHA_KEY, uuid, UUID.randomUUID().toString());
    }

    @Override
    ValidateCode generate(ServletRequest request, ServletResponse response) {
        String uuid = UUID.randomUUID().toString();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("image/gif");
        httpServletResponse.setHeader("Pragma", "No-cache");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setHeader(AppConstants.RESPONSE_CAPTCHA_KEY, uuid);
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        String result = captcha.text();// 获取运算的结果：5
        try {
            captcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ImageValidateCode(uuid, result);
    }
}
