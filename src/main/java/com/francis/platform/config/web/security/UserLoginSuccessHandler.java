package com.francis.platform.config.web.security;

import com.francis.platform.common.constans.AppProperties;
import com.francis.platform.common.response.CommonResponse;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.config.web.security.service.JwtService;
import com.francis.platform.util.RedisUtils;
import com.francis.platform.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    private final AppProperties appProperties;

    public UserLoginSuccessHandler(JwtService jwtService, AppProperties appProperties) {
        this.jwtService = jwtService;
        this.appProperties = appProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser user = (LoginUser) authentication.getPrincipal();
        try {
            String generateToken = jwtService.generateToken(user);
            RedisUtils.set(user.getUsername(), generateToken, appProperties.getJwt().getExpire() * 60);
            ResponseUtil.out(response, CommonResponse.Ok(generateToken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("[{}] 登录成功！",user.getUsername());
    }
}
