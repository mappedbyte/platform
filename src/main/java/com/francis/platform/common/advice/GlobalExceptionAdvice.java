package com.francis.platform.common.advice;

import com.francis.platform.common.exception.CustomException;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

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



    /**
     * @RequestBody 上校验失败后抛出的异常是 MethodArgumentNotValidException 异常。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String messages = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return CommonResponse.error(messages);
    }
    /**
     * 不加 @RequestBody注解，校验失败抛出的则是 BindException
     */
    @ExceptionHandler(value = BindException.class)
    public  CommonResponse<String> exceptionHandler(BindException e){
        String messages = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return CommonResponse.error(messages);
    }

    /**
     *  @RequestParam 上校验失败后抛出的异常是 ConstraintViolationException
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public CommonResponse<String> methodArgumentNotValid(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("；"));
        return CommonResponse.error(message);
    }

}
