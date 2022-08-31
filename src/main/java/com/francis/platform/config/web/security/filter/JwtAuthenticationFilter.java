package com.francis.platform.config.web.security.filter;

import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.common.constans.AppProperties;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.CommonResponse;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.config.web.security.service.JwtService;
import com.francis.platform.util.AuthorityTokenUtil;
import com.francis.platform.util.RedisUtils;
import com.francis.platform.util.ResponseUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public JwtAuthenticationFilter(JwtService jwtService, AppProperties appProperties) {

        this.jwtService = jwtService;
        this.appProperties = appProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authRequest = null;
        try {
            authRequest = getAuthentication(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        } else {
            SecurityContextHolder.clearContext();
        }
        if (response.isCommitted()) {
            return;
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getHeader(AppConstants.AUTHORITY_HEADER);
        if (token != null && (token.startsWith(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX)
                || token.startsWith(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX.toLowerCase())
        )) {
            token = token.replace(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX, "")
                    .replace(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX.toLowerCase(), "");
            LoginUser loginUser = AuthorityTokenUtil.parseUserInfoFromToken(token);
            if (RedisUtils.hasKey(loginUser.getUsername())&&RedisUtils.get(loginUser.getUsername()).equals(token)) {
                return new UsernamePasswordAuthenticationToken(loginUser.getUsername(), token, loginUser.getAuthorities());
            }
            if (RedisUtils.hasKey(loginUser.getUsername())&&!RedisUtils.get(loginUser.getUsername()).equals(token)) {
                ResponseUtil.out(response, CommonResponse.error(CommonCode.LOGIN_ELSE_WHERE));
                return null;
            }
            if (!RedisUtils.hasKey(AppConstants.BLACKLIST, token) ) {
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
