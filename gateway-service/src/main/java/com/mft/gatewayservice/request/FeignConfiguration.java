package com.mft.gatewayservice.request;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mfturkcan on 11.05.2022
 **/

@Configuration
public class FeignConfiguration {
    
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${feign.security.username}") String username,
            @Value("${feign.security.password}") String password
    ){
        return new BasicAuthRequestInterceptor(username, password);
    }
}