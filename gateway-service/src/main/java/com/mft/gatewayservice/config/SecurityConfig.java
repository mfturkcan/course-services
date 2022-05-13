package com.mft.gatewayservice.config;

import com.mft.gatewayservice.filter.JwtAuthFilter;
import com.mft.gatewayservice.model.Role;
import com.mft.gatewayservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
 @Autowired
 private CustomUserDetailsService customUserDetailsService;
 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
   http.cors();
   http.csrf().disable();
   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   
   http.authorizeRequests()
           .antMatchers("/api/authentication/**").permitAll()
           .antMatchers(HttpMethod.GET, "/api/gateway/**").permitAll()
           .antMatchers("/api/gateway/**").authenticated()
           .anyRequest().authenticated();
   
   http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
 }
 
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
 }
 
 @Bean
 public PasswordEncoder passwordEncoder(){
  return new BCryptPasswordEncoder();
 }
 
 @Bean(BeanIds.AUTHENTICATION_MANAGER)
 @Override
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }
 
 @Bean
 public WebMvcConfigurer configurer(){
  return new WebMvcConfigurer() {
   @Override
   public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("*");
   }
  };
 }
 
 @Bean
 public JwtAuthFilter jwtAuthFilter(){
  return new JwtAuthFilter();
 }
}