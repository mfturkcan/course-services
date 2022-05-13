package com.mft.gatewayservice.controller;

import com.mft.gatewayservice.model.User;
import com.mft.gatewayservice.service.AuthenticationService;
import com.mft.gatewayservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mfturkcan on 10.05.2022
 **/

@RestController
@RequestMapping("/api/authentication/")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    
    @Autowired
    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }
    
    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        return new ResponseEntity(authenticationService.signInReturnWithJwt(user), HttpStatus.OK);
    }
}