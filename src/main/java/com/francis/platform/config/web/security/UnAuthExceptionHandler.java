package com.francis.platform.config.web.security;

import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.CommonResponse;
import com.francis.platform.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Francis
 */
@Slf4j
public class UnAuthExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("AccessDeniedException : {}", accessDeniedException.getMessage());
        ResponseUtil.out(response, CommonResponse.error(CommonCode.PERMISSION_DENIED));
    }
}
