package com.francis.platform.controller;

import com.francis.platform.common.annotation.IgnoreResponseAdvice;
import com.francis.platform.common.captcha.ImageCodeGenerator;
import com.francis.platform.log.OperationLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Francis
 * @Date 2022-09-02 15:57
 **/
@RestController
@RequestMapping("/v1/captcha")
public class CaptchaController {


    @GetMapping("/obtain")
    @IgnoreResponseAdvice
    @OperationLog("测试")
    public void obtain(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCodeGenerator.me.create(request, response);
    }


}
