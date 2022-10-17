package com.francis.platform.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
//        converters.add(0, new MappingJackson2HttpMessageConverter());
        converters.add( new MappingJackson2HttpMessageConverter());
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
//                .allowedOrigins("*")
                .exposedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }





}
