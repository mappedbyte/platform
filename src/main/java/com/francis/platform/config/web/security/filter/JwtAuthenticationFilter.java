package com.francis.platform.config.web.security.filter;

import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.common.constans.AppProperties;
import com.francis.platform.common.exception.CustomSecurityException;
import com.francis.platform.common.exception.ExceptionCatch;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.config.web.security.service.JwtService;
import com.francis.platform.util.AuthorityTokenUtil;
import com.francis.platform.util.RedisUtils;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Francis
 */


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");


    private final JwtService jwtService;
    private final AppProperties appProperties;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public JwtAuthenticationFilter(JwtService jwtService, AppProperties appProperties, AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtService = jwtService;
        this.appProperties = appProperties;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authRequest = null;
        try {
            if (!requiresAuthentication(request)) {
                authRequest = getAuthentication(request, response);
                if (authRequest != null) {
                    SecurityContextHolder.getContext().setAuthentication(authRequest);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                this.authenticationEntryPoint.commence(request, response, (AuthenticationException) e);
            } else {
                this.authenticationEntryPoint.commence(request, response, new CustomSecurityException(CommonCode.UNAUTHORIZED_ERROR));
            }
        }


    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getHeader(AppConstants.AUTHORITY_HEADER);

        if (token != null && (token.startsWith(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX)
                || token.startsWith(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX.toLowerCase())
        )) {
            token = token.replace(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX, "")
                    .replace(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX.toLowerCase(), "");

            LoginUser loginUser = AuthorityTokenUtil.parseUserInfoFromToken(token);
            System.out.println("RedisUtils.hasKey(loginUser.getUsername():" + RedisUtils.hasKey(loginUser.getUsername()));
            if (RedisUtils.hasKey(loginUser.getUsername()) && RedisUtils.get(loginUser.getUsername()).equals(token)) {
                return new UsernamePasswordAuthenticationToken(loginUser.getUsername(), token, loginUser.getAuthorities());
            }
            if (RedisUtils.hasKey(loginUser.getUsername()) && !RedisUtils.get(loginUser.getUsername()).equals(token)
                /*  && !RedisUtils.hasKey(AppConstants.BLACKLIST, token)*/) {
                RedisUtils.hset(AppConstants.BLACKLIST, token, loginUser.getUsername(), 1800);
                ExceptionCatch.castSecurity(CommonCode.LOGIN_ELSE_WHERE);
            }
            if (!RedisUtils.hasKey(AppConstants.BLACKLIST, token)) {
                String generateToken = jwtService.generateToken(loginUser);
                response.setHeader(AppConstants.REFRESH_TOKEN, generateToken);
                RedisUtils.hset(AppConstants.BLACKLIST, token, loginUser.getUsername(), 1800);
                RedisUtils.set(loginUser.getUsername(), generateToken, appProperties.getJwt().getExpire() * 60);
                return new UsernamePasswordAuthenticationToken(loginUser.getUsername(), token, loginUser.getAuthorities());
            }
        }
        return null;
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
