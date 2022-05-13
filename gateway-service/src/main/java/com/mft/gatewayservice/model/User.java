package com.mft.gatewayservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mfturkcan on 9.05.2022
 **/

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    
    @Transient // Not persistent, Wont be added to database;
    private String token;
    
}