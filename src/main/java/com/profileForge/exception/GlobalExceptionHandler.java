package com.profileForge.exception;


import com.profileForge.dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException exception){

        ApiResponse apiResponse = ApiResponse.builder()
                .mesaage(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .sucess(true).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
