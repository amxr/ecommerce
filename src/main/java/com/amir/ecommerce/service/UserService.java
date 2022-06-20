package com.amir.ecommerce.service;

import com.amir.ecommerce.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto getUserDetails();
}
