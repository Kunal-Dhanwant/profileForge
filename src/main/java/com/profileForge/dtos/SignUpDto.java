package com.profileForge.dtos;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SignUpDto {

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message =" invalid user email!!")
    @NotBlank(message="email should not be empty")
    private  String email;

    @NotBlank(message = "password is required!!")
    @Size(min = 6,max = 20,message = "password should be minimum of  6 characters!!")
    private  String password;


}
