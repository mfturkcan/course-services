package com.mft.gatewayservice.util;

import com.ctc.wstx.util.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mfturkcan on 9.05.2022
 **/

public class SecurityUtil {
    private static final String ROLE_PREFIX= "Role_";
    private static final String HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    
    public static List<GrantedAuthority> convertToAuthorities(String role){
        role = role.contains(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return List.of(new SimpleGrantedAuthority(role));
    }
    
    public static String extractTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER);
        System.out.println(bearerToken);
        
        if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7);
        }
        
        return null;
    }
}