package com.mft.gatewayservice.service;

import com.mft.gatewayservice.model.CustomUserDetails;
import com.mft.gatewayservice.model.User;
import com.mft.gatewayservice.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Created by mfturkcan on 10.05.2022
 **/

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    
    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    
    @Override
    public User signInReturnWithJwt(User signInUser){
        
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(signInUser.getUsername(), signInUser.getPassword()
                        )); // Throws not shown !
        String token = jwtProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        
        signInUser.setToken(token);
        
        return signInUser;
    }
}