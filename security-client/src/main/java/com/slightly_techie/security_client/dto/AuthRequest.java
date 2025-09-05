package com.slightly_techie.security_client.dto;


import com.slightly_techie.security_client.entity.Role;
import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
    private Role role;
}
