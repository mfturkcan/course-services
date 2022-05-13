package com.mft.gatewayservice.util;

import com.mft.gatewayservice.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mfturkcan on 10.05.2022
 **/

public interface JwtProvider {
    String generateToken(CustomUserDetails user);
    
    Authentication getAuthenticationObject(HttpServletRequest request);
    
    boolean isTokenValid(HttpServletRequest request);
    
    Claims extractClaimsFromToken(HttpServletRequest request);
}