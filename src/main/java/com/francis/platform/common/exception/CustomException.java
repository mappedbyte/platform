package com.francis.platform.common.exception;


import com.francis.platform.common.response.ResultCode;

/**
 * @author Francis
 */
public class CustomException extends RuntimeException{

    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode=resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }






}
