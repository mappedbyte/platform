package com.francis.platform.config.web.security;

import com.francis.platform.common.response.CommonResponse;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.config.web.security.service.JwtService;
import com.francis.platform.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Francis
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    public UserLoginSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser user = (LoginUser) authentication.getPrincipal();
        try {
            String generateToken = jwtService.generateToken(user);
            ResponseUtil.out(response, CommonResponse.Ok(generateToken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登录成功！！！");

    }
}
