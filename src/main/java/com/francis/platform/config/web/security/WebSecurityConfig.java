package com.francis.platform.config.web.security;

import com.francis.platform.config.web.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Francis
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig   {


    private final UserDetailsService userDetailsService;
    private final UserLoginSuccessHandler userLoginSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public WebSecurityConfig(UserDetailsService userDetailsService, UserLoginSuccessHandler userLoginSuccessHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.userLoginSuccessHandler = userLoginSuccessHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }




    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().antMatchers("/platform");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new UnAuthEntryPoint())
                .accessDeniedHandler(new UnAuthExceptionHandler())
                .and()
                .formLogin()
                 .loginProcessingUrl("/login").successHandler(userLoginSuccessHandler)
                .failureHandler(new UnAuthenticationFailureHandler())
                .and().logout().logoutUrl("/logout")
                .addLogoutHandler(new UserLogoutSuccessHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/platform/login")
                .permitAll().antMatchers("/app/test").hasAnyRole("ROLE")
                .anyRequest()
                .authenticated()
                .and().userDetailsService(userDetailsService);
        return http.build();
    }









}
