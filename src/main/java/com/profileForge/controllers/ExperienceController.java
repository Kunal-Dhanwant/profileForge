package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.ExperienceDto;
import com.profileForge.models.Experience;
import com.profileForge.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Experince CRUD Rest Api"
)
@RestController
@RequestMapping("/experience")
public class ExperienceController {


    @Autowired
    private ExperienceService experienceService;


    @Operation(
            summary = "Add Education RestApi",
            description = "This is used to add the new Education of the user"
    )

    @PostMapping("/user/{userId}")
    public ResponseEntity<ExperienceDto>  addExperience(@RequestBody ExperienceDto experienceDto,@PathVariable  String userId){

        ExperienceDto experienceDto1 = experienceService.addExperience(userId,experienceDto);

        return  new ResponseEntity<>(experienceDto1, HttpStatus.CREATED);





    }
    @Operation(
            summary = "Delete Education RestApi",
            description = "This is used to delete the  Education of the user"
    )

    @DeleteMapping("/id/{experienceId}/user/{userId}")
    public ResponseEntity<ApiResponse>  deleteExperience(@PathVariable String experienceId, @PathVariable  String userId){

      experienceService.deleteExperince(userId,experienceId);
      ApiResponse apiResponse = ApiResponse.builder()
              .sucess(true).status(HttpStatus.OK)
              .mesaage("experience delted successfully")
              .build();

      return  new ResponseEntity<>(apiResponse,HttpStatus.OK);





    }

    @Operation(
            summary = "Fetch All Education RestApi",
            description = "This is used to fetch all the  Education of the user"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExperienceDto>>  getAllExperince(@PathVariable String userId){

        List<ExperienceDto>  experienceDtoList = experienceService.getAllExperience(userId);

        return  new ResponseEntity<>(experienceDtoList,HttpStatus.OK);

    }
    @Operation(
            summary = "Update Education RestApi",
            description = "This is used to update the new Education of the user"
    )

    @PutMapping("/id/{educationId}")
    public ResponseEntity<ExperienceDto>  updateExperience(@RequestBody ExperienceDto experienceDto,@PathVariable  String educationId){

        ExperienceDto experienceDto1 = experienceService.updateExperience(experienceDto,educationId);

        return  new ResponseEntity<>(experienceDto1, HttpStatus.OK);


    }


}
