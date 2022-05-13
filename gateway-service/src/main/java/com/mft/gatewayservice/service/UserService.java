package com.mft.gatewayservice.service;

import com.mft.gatewayservice.model.Role;
import com.mft.gatewayservice.model.User;

import java.util.Optional;

/**
 * Created by mfturkcan on 9.05.2022
 **/

public interface UserService {
    User saveUser(User user);
    
    void changeRole(String username, Role role);
    
    Optional<User> findByUsername(String username);
    
    void deleteUser(long userId);
}