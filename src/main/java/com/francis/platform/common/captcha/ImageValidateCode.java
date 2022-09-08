package com.francis.platform.common.captcha;


/**
 * @Author Francis
 * @Date 2022-09-02 17:51
 **/

public class ImageValidateCode extends ValidateCode {

    public ImageValidateCode(String validateKey, String generateResult) {
        this.generateResult = generateResult;
        this.validateKey = validateKey;
    }
}
