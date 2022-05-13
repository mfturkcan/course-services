package com.mft.gatewayservice.util;

import com.mft.gatewayservice.model.CustomUserDetails;
import com.mft.gatewayservice.model.Role;
import com.mft.gatewayservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by mfturkcan on 10.05.2022
 **/

@Component
public class JwtProviderImpl implements JwtProvider {
    
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;
    
    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;
    
    @Override
    public String generateToken(CustomUserDetails user){
        String authorities = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", authorities)
                .claim("userId", user.getUser().getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    
    @Override
    public Authentication getAuthenticationObject(HttpServletRequest request){
        Claims claims = extractClaimsFromToken(request);
        if(claims == null) return null;
        
        String username = claims.getSubject();
        long userId = claims.get("userId", Long.class);
        Role role = Role.valueOf(claims.get("roles", String.class).substring(5));
        User user = new User();
        user.setUsername(username);
        user.setId(userId);
        user.setRole(role);
        
        CustomUserDetails userDetails = CustomUserDetails.builder()
                .user(user)
                .build();
        
        if(username == null) return null;
        return new UsernamePasswordAuthenticationToken(userDetails, null, SecurityUtil.convertToAuthorities(role.toString()));
    }
    
    @Override
    public boolean isTokenValid(HttpServletRequest request){
        Claims claims = extractClaimsFromToken(request);
        
        if(claims == null || claims.getExpiration().before(new Date())) return false;
        
        return true;
    }
    
    @Override
    public Claims extractClaimsFromToken(HttpServletRequest request){
        String token = SecurityUtil.extractTokenFromRequest(request);
        
        if(token == null) return null;
        
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}