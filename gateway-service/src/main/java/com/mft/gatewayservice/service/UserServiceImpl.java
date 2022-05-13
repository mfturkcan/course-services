package com.mft.gatewayservice.service;

import com.mft.gatewayservice.model.Role;
import com.mft.gatewayservice.model.User;
import com.mft.gatewayservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }
    
    @Override
    public User saveUser(User user){
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        
        userRepository.save(user);
        
        return user;
    }
    
    @Transactional
    @Override
    public void changeRole(String username, Role role){
        userRepository.updateUserRole(username, role);
    }
    
    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username); // TODO: Resource not found exception will be added
    }
    
    @Override
    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }
}