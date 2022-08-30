package com.francis.platform.config.web.security.filter;

import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.util.AuthorityTokenUtil;
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
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authRequest = null;
        try {
            authRequest = getAuthentication(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        } else {
            SecurityContextHolder.clearContext();

        }
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws Exception {
        String token = request.getHeader(AppConstants.AUTHORITY_HEADER);
        if (token != null && (token.startsWith(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX)
        ||token.startsWith(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX.toLowerCase())
        )) {
            token = token.replace(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX, "")
                    .replace(AppConstants.AUTHORITY_HEADER_CONTENT_PREFIX.toLowerCase(),"");
            LoginUser loginUser = AuthorityTokenUtil.parseUserInfoFromToken(token);
            return new UsernamePasswordAuthenticationToken(loginUser.getUsername(), token, loginUser.getAuthorities());
        }
        return null;
    }

}
