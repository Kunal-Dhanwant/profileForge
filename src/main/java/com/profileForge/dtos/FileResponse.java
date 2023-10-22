package com.profileForge.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {

    private String fileName;
    private String message;
    private boolean success;
    private HttpStatus status;
}
