package com.francis.platform.common.exception;

import com.francis.platform.common.response.ResultCode;
import org.springframework.security.core.AuthenticationException;

public class CustomSecurityException extends AuthenticationException {

    ResultCode resultCode;
    public CustomSecurityException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomSecurityException(ResultCode resultCode) {
        super(resultCode.message(), null);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }



}
