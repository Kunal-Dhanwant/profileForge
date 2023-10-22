package com.profileForge.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class LoginRequest {

    private  String email;
    private String password;
}
