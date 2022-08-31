package com.francis.platform.common.advice;

import com.francis.platform.common.exception.CustomException;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception capture processing
 * @author Francis
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<String> handlerCustomException(Exception e) {
        log.error("common service has CustomException: [{}]", e.getMessage(), e);
        return CommonResponse.error(((CustomException) e).getResultCode());
    }




    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<String> handlerCommonException(Exception e) {
        log.error("common service has error: [{}]", e.getMessage(), e);
        return CommonResponse.error(CommonCode.ERROR);
    }


}
