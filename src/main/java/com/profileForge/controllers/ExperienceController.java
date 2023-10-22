package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.ExperienceDto;
import com.profileForge.models.Experience;
import com.profileForge.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {


    @Autowired
    private ExperienceService experienceService;


    @PostMapping("/user/{userId}")
    public ResponseEntity<ExperienceDto>  addExperience(@RequestBody ExperienceDto experienceDto,@PathVariable  String userId){

        ExperienceDto experienceDto1 = experienceService.addExperience(userId,experienceDto);

        return  new ResponseEntity<>(experienceDto1, HttpStatus.CREATED);





    }

    @DeleteMapping("/id/{experienceId}/user/{userId}")
    public ResponseEntity<ApiResponse>  deleteExperience(@PathVariable String experienceId, @PathVariable  String userId){

      experienceService.deleteExperince(userId,experienceId);
      ApiResponse apiResponse = ApiResponse.builder()
              .sucess(true).status(HttpStatus.OK)
              .mesaage("experience delted successfully")
              .build();

      return  new ResponseEntity<>(apiResponse,HttpStatus.OK);





    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExperienceDto>>  getAllExperince(@PathVariable String userId){

        List<ExperienceDto>  experienceDtoList = experienceService.getAllExperience(userId);

        return  new ResponseEntity<>(experienceDtoList,HttpStatus.OK);

    }

    @PutMapping("/id/{educationId}")
    public ResponseEntity<ExperienceDto>  updateExperience(@RequestBody ExperienceDto experienceDto,@PathVariable  String educationId){

        ExperienceDto experienceDto1 = experienceService.updateExperience(experienceDto,educationId);

        return  new ResponseEntity<>(experienceDto1, HttpStatus.OK);


    }


}
