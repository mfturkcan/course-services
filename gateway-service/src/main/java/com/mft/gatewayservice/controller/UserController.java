package com.mft.gatewayservice.controller;

import com.mft.gatewayservice.model.CustomUserDetails;
import com.mft.gatewayservice.model.Role;
import com.mft.gatewayservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mfturkcan on 11.05.2022
 **/

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserService userService;
  
  
  @PutMapping("/change/{role}")
  public ResponseEntity<?> changeRole(@PathVariable Role role){ // @AuthenticationPrincipal also holds principal object
    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  
    userService.changeRole(userDetails.getUsername(), role);
    
    return ResponseEntity.ok().build();
  }
}