package com.profileForge.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class LoginRequest {
    @NotBlank(message = "please provide email")

    private  String email;

    @NotBlank(message = "please provide password")

    private String password;
}
