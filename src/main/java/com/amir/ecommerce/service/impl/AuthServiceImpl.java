package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.controller.request.LoginCredentials;
import com.amir.ecommerce.controller.request.SignUpRequest;
import com.amir.ecommerce.mapper.UserMapper;
import com.amir.ecommerce.security.jwt.JWTToken;
import com.amir.ecommerce.model.Role;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.repository.UserRepository;
import com.amir.ecommerce.security.jwt.JWTUtil;
import com.amir.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Override
    public JWTToken register(SignUpRequest signUpRequest) {
        if(userRepository.findUserByEmail(signUpRequest.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User exist!");
        }

        User user = UserMapper.toUser(signUpRequest);
        user.setRole(Role.CUSTOMER);
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        JWTToken jwtToken = new JWTToken();
        jwtToken.setJwtToken(token);
        return jwtToken;
    }

    @Override
    public JWTToken login(LoginCredentials loginCredentials){

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(), loginCredentials.getPassword());

        authManager.authenticate(authInputToken);

        String token = jwtUtil.generateToken(loginCredentials.getEmail());
        JWTToken jwtToken = new JWTToken();
        jwtToken.setJwtToken(token);
        return jwtToken;
    }
}
