package com.francis.platform.config.web.security;

import com.francis.platform.common.exception.CustomSecurityException;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.CommonResponse;
import com.francis.platform.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Francis
 */
@Component
public class UnAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof CustomSecurityException) {
            ResponseUtil.out(response, CommonResponse.error(((CustomSecurityException)authException).getResultCode()));
        }

        ResponseUtil.out(response, CommonResponse.error(CommonCode.UNAUTHORIZED_ERROR));
    }
}
