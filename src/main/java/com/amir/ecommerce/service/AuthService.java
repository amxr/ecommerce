package com.amir.ecommerce.service;

import com.amir.ecommerce.controller.request.LoginCredentials;
import com.amir.ecommerce.controller.request.SignUpRequest;
import com.amir.ecommerce.security.jwt.JWTToken;

public interface AuthService {
    JWTToken register(SignUpRequest signUpRequest);

    JWTToken login(LoginCredentials loginCredentials);
}
