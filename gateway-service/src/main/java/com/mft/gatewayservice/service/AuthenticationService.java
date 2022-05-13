package com.mft.gatewayservice.service;

import com.mft.gatewayservice.model.User;

/**
 * Created by mfturkcan on 10.05.2022
 **/

public interface AuthenticationService {
    User signInReturnWithJwt(User signInUser);
}