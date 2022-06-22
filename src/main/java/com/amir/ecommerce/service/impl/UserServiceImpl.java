package com.amir.ecommerce.service.impl;

import com.amir.ecommerce.dto.UserDto;
import com.amir.ecommerce.mapper.UserMapper;
import com.amir.ecommerce.model.User;
import com.amir.ecommerce.repository.UserRepository;
import com.amir.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s, not found.", username)
                ));
    }

    @Override
    public UserDto getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s, not found.", email)
                ));

        return UserMapper.toUserDto(user);
    }
}
