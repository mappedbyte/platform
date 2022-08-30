package com.francis.platform.util;


import com.alibaba.fastjson.JSON;
import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.config.web.security.entity.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * JWT token解析工具类
 */
@Slf4j
public class AuthorityTokenUtil {



    /**
     * 从 JWT Token 解析LoginUserInfo对象
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static LoginUser parseUserInfoFromToken(String token) throws Exception {
        if (null == token) {
            return null;
        }

        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            //如果token已经过期了 返回null
            throw new ExpiredJwtException(claimsJws.getHeader(), claimsJws.getBody(), "token已经过期！");
        }

        return JSON.parseObject(body.get(AppConstants.JWT_USER_INFO_KEY).toString(), LoginUser.class);
    }





    /**
     * 通过公钥去解析 JWT Token
     *
     * @param token
     * @param publicKey
     * @return
     */
    private static Jws<Claims> parseToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token);
    }


    public static final String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkbQS2CMRvB3e9l+zbxDq1ds/CW1h7dmpd1aJZJR2/qpC2itreI6AdUVquESx564I+M1UPwDW6qqhhrz7wFiGs2GGr59TkQ0rf2+WsNFWblAQoyC/Dxr5s60oBsMpx8hMwbs2ZU5vHFlkhFuLSc6kDEb2SSpLAx5XtTUZw3ZcSMy4tZFUjUU1YCRxT89idgpyLt2p8XxMF8HKQxClXeF1arAsvDVCFbAhYURyubfPhrFSh80CxfgoyLeqLEdGqZb/kHDsKTPQZ8yVOLX/Dd/xt+7xdE2MRIKhmB6EnjnWERuJ1xDWIwLhOYbVGyBXrGn+3EIMtoYtgkXlbAVjJACi2wIDAQAB";


    /**
     * 根据本地获取的公钥获取到PublicKey 对象
     *
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder()
                .decodeBuffer(PUBLIC_KEY));
        return KeyFactory.getInstance("RSA")
                .generatePublic(keySpec);
    }



}
