package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.controller.request.LoginCredentials;
import com.amir.ecommerce.controller.request.SignUpRequest;
import com.amir.ecommerce.security.jwt.JWTToken;
import com.amir.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public JWTToken register(@RequestBody SignUpRequest signUpRequest){
        return authService.register(signUpRequest);
    }

    @PostMapping("/login")
    public JWTToken login(@RequestBody LoginCredentials loginCredentials){
        return authService.login(loginCredentials);
    }
}
