package com.mft.gatewayservice.model;

import com.mft.gatewayservice.util.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Component
@Getter
@Builder
public class CustomUserDetails implements UserDetails {
    
    private transient User user;
    
    public CustomUserDetails(User user) {
        this.user = user;
    }
    
    public CustomUserDetails(){}
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityUtil.convertToAuthorities(user.getRole().name());
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUsername();
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
}