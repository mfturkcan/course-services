package com.mft.gatewayservice.repository;

import com.mft.gatewayservice.model.Role;
import com.mft.gatewayservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by mfturkcan on 9.05.2022
 **/

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    @Modifying // Change db by this query. Includes flush automatically.
    @Query("update User set role= :role where username= :username") // : for values
    void updateUserRole(@Param("username") String username, @Param("role") Role role);
}