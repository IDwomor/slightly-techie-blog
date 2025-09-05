package com.slightly_techie.security_client.dto;


import com.slightly_techie.security_client.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String username;
    //private String password;
    private String token;
    private String role;
}
