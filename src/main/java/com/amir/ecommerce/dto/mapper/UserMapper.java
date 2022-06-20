package com.amir.ecommerce.dto.mapper;

import com.amir.ecommerce.controller.request.SignUpRequest;
import com.amir.ecommerce.dto.UserDto;
import com.amir.ecommerce.model.User;

public class UserMapper {
    public static User toUser(SignUpRequest signUpRequest){
        return new User()
                .setFirstName(signUpRequest.getFirstName())
                .setLastName(signUpRequest.getLastName())
                .setEmail(signUpRequest.getEmail());
    }

    public static UserDto toUserDto(User user){
        return new UserDto()
                .setEmail(user.getEmail())
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }
}
