package com.profileForge.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class LoginResponse {

    private  String jwtToken;
    private UserDto user;
}
