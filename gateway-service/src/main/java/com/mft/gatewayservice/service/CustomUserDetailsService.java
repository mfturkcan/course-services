package com.mft.gatewayservice.service;

import com.mft.gatewayservice.model.CustomUserDetails;
import com.mft.gatewayservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
 @Autowired
 private UserService userService;
 
 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
  User user = userService.findByUsername(username)
          .orElseThrow(()-> new UsernameNotFoundException("User not found with username : "+username));
  
  return CustomUserDetails.builder()
          .user(user)
          .build();
 }
}