package com.francis.platform.config.web.security;

import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.CommonResponse;
import com.francis.platform.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Francis
 */
public class UnAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, CommonResponse.error(CommonCode.UNAUTHORIZED_ERROR));
    }
}
