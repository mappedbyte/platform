package com.francis.platform.common.constans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Francis
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    public static Boolean ipLocal;

    @Value("${ip.local-parsing}")
    public void setIpLocal(Boolean ipLocal) {
        AppProperties.ipLocal = ipLocal;
    }



    private Jwt jwt = new Jwt();



    @Data

    public static class Jwt {
        public int expire;
    }


}
