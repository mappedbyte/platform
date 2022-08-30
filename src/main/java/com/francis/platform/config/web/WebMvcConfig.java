package com.francis.platform.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
/**
 * @author Francis
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Unified processing of ResponseBodyAdvice, when the return value is String type,
     * there will be a conversion exception. Because StringHttpMessageConverter has a higher priority
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }


}
