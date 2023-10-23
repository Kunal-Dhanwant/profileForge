package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.EducationDto;
import com.profileForge.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST Api For Eductaion "
)
@Slf4j
@RequestMapping("/education")
@RestController
public class EducationController {

    @Autowired
    private EducationService educationService;

@Operation(
        summary = "Create new Education Rest Api"
        ,description = "This is used to add new education of the user"
)
    @PostMapping("/user/{userId}")
    public ResponseEntity<EducationDto> addEducation(@RequestBody EducationDto educationDto, @PathVariable String userId){


        EducationDto educationDto1 = educationService.addEducation(educationDto, userId);

        return  new ResponseEntity<>(educationDto, HttpStatus.CREATED);


    }

    @Operation(
            summary = "Update new Education Rest Api"
            ,description = "This is used to update the education of the user"
    )

    @PutMapping("/{educationId}")
    public ResponseEntity<EducationDto> updateEducation(@RequestBody EducationDto educationDto, @PathVariable String educationId){


        EducationDto educationDto1 = educationService.updateEducation(educationDto, educationId);

        return  new ResponseEntity<>(educationDto1, HttpStatus.OK);


    }

    @Operation(
            summary = "Delete new Education Rest Api"
            ,description = "This is used to delete  education of the user"
    )


    @DeleteMapping("/{educationId}/user/{userId}")
    public ResponseEntity<ApiResponse> deleteEducation(@PathVariable String educationId,@PathVariable String  userId){



        educationService.deleteEducation(educationId,userId);


        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.OK)
                .mesaage("Education deleted succesfully")
                .sucess(true).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @Operation(
            summary = "Fetch ALL Education Rest Api"
            ,description = "This is used to add fetch all education of the user"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EducationDto>> getAllEducationByUser(@PathVariable String userId){

        List<EducationDto> educationDtoList = educationService.getAllEducationOfUser(userId);

        return  new ResponseEntity<>(educationDtoList,HttpStatus.OK);

    }





}
