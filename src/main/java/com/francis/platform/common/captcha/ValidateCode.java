package com.francis.platform.common.captcha;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Francis
 * @Date 2022-09-02 17:52
 **/

public abstract class ValidateCode implements Serializable {

      String validateKey;
      String generateResult;

}
