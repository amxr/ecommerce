package com.amir.ecommerce.controller.api;

import com.amir.ecommerce.controller.request.SignUpRequest;
import com.amir.ecommerce.dto.UserDto;
import com.amir.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/info")
    public UserDto getUserDetails(){
        return userService.getUserDetails();
    }
}
