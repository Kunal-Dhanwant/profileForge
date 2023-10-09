package com.profileForge.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {

    private String mesaage;
    private boolean sucess;
    private HttpStatus status;
}
