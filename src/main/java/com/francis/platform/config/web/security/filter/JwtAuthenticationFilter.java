package com.francis.platform.config.web.security.filter;

import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.common.constans.AppProperties;
import com.francis.platform.common.exception.ExceptionCatch;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.config.web.security.service.JwtService;
import com.francis.platform.util.AuthorityTokenUtil;
import com.francis.platform.util.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Francis
 */


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


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
            authRequest = getAuthentication(request, response);
            if (authRequest != null) {
                SecurityContextHolder.getContext().setAuthentication(authRequest);
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                this.authenticationEntryPoint.commence(request, response, (AuthenticationException) e);
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

            if (RedisUtils.hasKey(loginUser.getUsername()) && RedisUtils.get(loginUser.getUsername()).equals(token)) {
                return new UsernamePasswordAuthenticationToken(loginUser.getUsername(), token, loginUser.getAuthorities());
            }
            if (RedisUtils.hasKey(loginUser.getUsername()) && !RedisUtils.get(loginUser.getUsername()).equals(token)
                    && !RedisUtils.hasKey(AppConstants.BLACKLIST, token)) {
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

}
