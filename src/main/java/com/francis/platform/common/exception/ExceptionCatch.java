package com.francis.platform.common.exception;

import com.francis.platform.common.response.ResultCode;

/**
 * @author Francis
 */
public class ExceptionCatch {
    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }

    public static void castSecurity(ResultCode resultCode){
        throw new CustomSecurityException(resultCode);
    }

}
