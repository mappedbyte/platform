package com.francis.platform.config.web.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.francis.platform.config.web.security.entity.LoginUser;
import com.francis.platform.entity.User;
import com.francis.platform.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Francis
 */
@Component
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getOne(new QueryWrapper<User>()
                .lambda().eq(User::getUsername, username));
        if (user != null) {
            return LoginUser.userToLoginUser(user, Collections.emptyList());
        }
        return null;
    }
}
