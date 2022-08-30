package com.francis.platform.config.web.security.entity;

import com.francis.platform.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Francis
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {
    private Long userId;
    private String username;
    private String nickName;
    private String password;
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public  static  LoginUser userToLoginUser(User user, List<GrantedAuthority> authorities) {
        return LoginUser.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .username(user.getUsername())
                .authorities(authorities)
                .build();
    }


}
