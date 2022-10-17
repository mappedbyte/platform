package com.francis.platform.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Francis
 */
@Data
public class CommonResponse<T> implements Response, Serializable {
    private Boolean success = SUCCESS;

    private Integer code = SUCCESS_CODE;

    private String message = MESSAGE;

    private T data;

    private CommonResponse() {

    }

    public static <T> CommonResponse<T> Ok(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setData(data);
        return commonResponse;
    }

    public static <T> CommonResponse<T> Ok() {
        return new CommonResponse<>();
    }

    public static <T> CommonResponse<T> error(ResultCode commonCode){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(commonCode.code());
        commonResponse.setMessage(commonCode.message());
        commonResponse.setSuccess(commonCode.success());
        return commonResponse;
    }




    public static <T> CommonResponse<T> error(String message){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(CommonCode.ERROR.code);
        commonResponse.setMessage(message);
        commonResponse.setSuccess(CommonCode.ERROR.success);
        return commonResponse;
    }
}
