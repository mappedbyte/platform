package com.francis.platform.config.web.security;

import com.francis.platform.common.constans.AppProperties;
import com.francis.platform.config.web.security.filter.CaptchaFilter;
import com.francis.platform.config.web.security.filter.JwtAuthenticationFilter;
import com.francis.platform.config.web.security.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Francis
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final UserDetailsService userDetailsService;
    private final UserLoginSuccessHandler userLoginSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CaptchaFilter captchaFilter;
    private final JwtService jwtService;
    private final AppProperties appProperties;


    private final AuthenticationEntryPoint authenticationEntryPoint;

    public WebSecurityConfig(UserDetailsService userDetailsService, UserLoginSuccessHandler userLoginSuccessHandler, CaptchaFilter captchaFilter, JwtService jwtService, AppProperties appProperties, AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.userLoginSuccessHandler = userLoginSuccessHandler;
        this.captchaFilter = captchaFilter;
        this.jwtService = jwtService;
        this.appProperties = appProperties;
        this.authenticationEntryPoint = authenticationEntryPoint;

        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(this.jwtService, this.appProperties, this.authenticationEntryPoint);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/v1/captcha/obtain","/v1/siteArea/push" );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(captchaFilter, JwtAuthenticationFilter.class)
                .authorizeRequests(authorize -> authorize.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/v1/captcha/obtain").permitAll()
                        .antMatchers("/v1/siteArea/push").permitAll()
                        .anyRequest()
                        .authenticated()
                ).formLogin()
                .successHandler(userLoginSuccessHandler)
                .failureHandler(new UnAuthenticationFailureHandler())
                .and().logout()
                .addLogoutHandler(new UserLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(new UnAuthExceptionHandler())
                .and()
                .userDetailsService(userDetailsService);
        return http.build();
    }


}
