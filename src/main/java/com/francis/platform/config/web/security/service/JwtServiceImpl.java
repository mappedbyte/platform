package com.francis.platform.config.web.security.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.config.web.security.entity.LoginUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

/**
 * @author Francis
 */
@Service
public class JwtServiceImpl  implements JwtService{

    /**
     * 根据本地存储的私钥获取到 PrivateKey对象
     * @return
     * @throws Exception
     */
    private PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(PRIVATE_KEY)
        );
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }

    public static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRtBLYIxG8Hd72X7NvEOrV2z8JbWHt2al3VolklHb+qkLaK2t4joB1RWq4RLHnrgj4zVQ/ANbqqqGGvPvAWIazYYavn1ORDSt/b5aw0VZuUBCjIL8PGvmzrSgGwynHyEzBuzZlTm8cWWSEW4tJzqQMRvZJKksDHle1NRnDdlxIzLi1kVSNRTVgJHFPz2J2CnIu3anxfEwXwcpDEKVd4XVqsCy8NUIVsCFhRHK5t8+GsVKHzQLF+CjIt6osR0aplv+QcOwpM9BnzJU4tf8N3/G37vF0TYxEgqGYHoSeOdYRG4nXENYjAuE5htUbIFesaf7cQgy2hi2CReVsBWMkAKLbAgMBAAECggEAZdG3EX+AVztwoi9Cjyz9TrFGHepocvSWbqT0omr1tQV6bkfFdJvwvQLjjx3CTSrbbjLe2qBVj+vpsOTD8ShsCVLv8i6fEVBow0dRwQKpKbGdzsGFgqLROHSa8g7UVZ5ttTyEyMrE7sSxb6Uhc1SKY2H7nimF/gnoNOOEu4ks+Fm5vkGBa8jOBRr497i0RMZx3ns/wRIDKFK5sKdOPd7V4CcFszDBacpXa9ggLMrfDO5WdH2yzsZVEbhJFdiP/m6Q/3SOQplPpFFEf/vj3jLa2AVp3JpYaMY4VNqMeczbwzfFPvms+RUqz3R7l10DsQstG0XqWhO01FKY8LhvKvJx8QKBgQDLBDrBIQgrUqSy5FoAbjjAue1m8Bsbfk0SwV6WMVdpAfeFTnOBag1/1BZs7jUaNv76Ngpv7RjfeE51aatvTurEW/awLRe0D+48ofZL9hwDBuyNzpZDTUpIhqnJapK9B0bWgWrGQFvqZMBGe4HowiaflKeRGoV9ME4S5bt/fwYV/QKBgQC3urA/qJhu2Qzfr6fNeto7ElQu26wi2MQokZN6b1pvsF2qJ8U8vOAfdEvzO1g/0MpC7aWCwdYE+S5DXCOg8BhfwDDGdYtSxGqquqIHqlvEU8sBq3vCcobbBjF6nZ2ywaiPqBH3loevOQmeBMG1v41yZ8W9EMihKRwuq4f4Q1EHtwKBgBoS2/zr4iG2RoLXQxvlDBLsjin3UnPwf7OV9DDz+1b4MrMDRWBsn3k4Yvzwhw9r2smF4UdIuzYQPHtl6+eqygoYCZHM6MtudFmDL5/H5wcYf48i5jeUmKLB8NJ2howSgC2Sh4UYxPVQpUUoHaIoNU+vhuni7xwJ9S0GJlOUm9J9AoGAY2mzli5elJQDIEHGdSuFS7NGM7iPMqfBtAXZG8WG1KpEdB5i3dOBHePqeKjivlz2JDK8fLzvVe+SdcY0TQ+80IqImcneYiF/afPkWmt2jguc68FjDLq1d2upeCQywReCYoDZf/NGZaxiJMuEGRiVq5K07vFW7cT1x9bukomyHY8CgYBky/UKyJmyGUHoXyiNPNUPC9+XNnqd0bqBoWaLt2mu4JIEN2mqBafE38N78H4CKmCRHG9P4yblRxYEWoRMCpi/x0/JfCDyap8fgBIclpOeVbglzqPHFyZkvxXI46vUq3phdzlQHaY+VAUrIS3ogDNx9Ea/uJMQSjZkpwXD6Pi/1Q==";

    @Override
    public String generateToken(LoginUser user) throws Exception {
      return   generateToken(user, 1);
    }

    @Override
    public String generateToken(LoginUser user, int expire) throws Exception {
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());
        return Jwts.builder()
//              payload 部分
                .claim(AppConstants.JWT_USER_INFO_KEY, JSONObject.toJSONString(user))
//              jwt id
                .setId(UUID.randomUUID().toString())
//              jwt 过期时间
                .setExpiration(expireDate)
//              签名
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }
}
