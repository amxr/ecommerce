package com.amir.ecommerce.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginCredentials {
    private String email;
    private String password;
}
