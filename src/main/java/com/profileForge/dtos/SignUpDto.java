package com.profileForge.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SignUpDto {


    private  String email;
    private  String password;


}
