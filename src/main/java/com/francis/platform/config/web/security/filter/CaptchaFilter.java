package com.francis.platform.config.web.security.filter;

import com.francis.platform.common.captcha.ImageCodeGenerator;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Francis
 * @Date 2022-09-02 17:24
 **/


@Component
public class CaptchaFilter extends OncePerRequestFilter {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    public CaptchaFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (requiresAuthentication(request)) {
                ImageCodeGenerator.me.validate(request, response);
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            this.authenticationEntryPoint.commence(request, response, e);
        }

    }


    protected boolean requiresAuthentication(HttpServletRequest request) {
        if (this.DEFAULT_ANT_PATH_REQUEST_MATCHER.matches(request)) {
            return true;
        }
        if (this.logger.isTraceEnabled()) {
            this.logger
                    .trace(LogMessage.format("Did not match request to %s", this.DEFAULT_ANT_PATH_REQUEST_MATCHER));
        }
        return false;
    }


}
