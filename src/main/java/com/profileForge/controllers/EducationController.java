package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.EducationDto;
import com.profileForge.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/education")
@RestController
public class EducationController {

    @Autowired
    private EducationService educationService;


    @PostMapping("/user/{userId}")
    public ResponseEntity<EducationDto> addEducation(@RequestBody EducationDto educationDto, @PathVariable String userId){


        EducationDto educationDto1 = educationService.addEducation(educationDto, userId);

        return  new ResponseEntity<>(educationDto, HttpStatus.CREATED);


    }



    @PutMapping("/{educationId}")
    public ResponseEntity<EducationDto> updateEducation(@RequestBody EducationDto educationDto, @PathVariable String educationId){


        EducationDto educationDto1 = educationService.updateEducation(educationDto, educationId);

        return  new ResponseEntity<>(educationDto1, HttpStatus.OK);


    }


    @DeleteMapping("/{educationId}/user/{userId}")
    public ResponseEntity<ApiResponse> deleteEducation(@PathVariable String educationId,@PathVariable String  userId){



        educationService.deleteEducation(educationId,userId);


        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.OK)
                .mesaage("Education deleted succesfully")
                .sucess(true).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EducationDto>> getAllEducationByUser(@PathVariable String userId){

        List<EducationDto> educationDtoList = educationService.getAllEducationOfUser(userId);

        return  new ResponseEntity<>(educationDtoList,HttpStatus.OK);

    }





}
