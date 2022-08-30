package com.francis.platform.config.web.security.service;


import com.francis.platform.config.web.security.entity.LoginUser;

/**
 * @author Francis
 */
public interface JwtService {

    String generateToken(LoginUser user) throws Exception;


    String generateToken(LoginUser user,int expire) throws Exception;



}
