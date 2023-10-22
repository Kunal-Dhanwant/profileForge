package com.profileForge.exception;


import com.profileForge.dtos.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    // MethodArgumentNotValidException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handelMethodArgumentNotValidException(MethodArgumentNotValidException exception){


        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        Map<String,Object> response = new HashMap<>();
        allErrors.stream().forEach(objectError->{

            String message = objectError.getDefaultMessage();
            String field = ((FieldError)objectError).getField();
            response.put(field,message);

        });

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handelMethodArgumentNotValidException(DataIntegrityViolationException exception){

        return  new ResponseEntity<>( exception.getCause().getMessage(),HttpStatus.CONFLICT);


    }

    //  handerl api bad api request
    @ExceptionHandler(ImageBadApiRequest.class)
    public ResponseEntity<ApiResponse> imageBadApiRequestHandler(ImageBadApiRequest exception){

       // log.info("Bad api Request");

        ApiResponse response = ApiResponse.builder()
                .mesaage(exception.getMessage())
                .sucess(false)
                .status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);

    }




}
