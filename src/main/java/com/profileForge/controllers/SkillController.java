package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.SkillsDto;
import com.profileForge.service.SkillsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
      name = "Skill CRUD RestApi"
)
@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillsService skillsService;


    @Operation(
            summary = "Add Skill RestApi"
            ,description = "This is used to add the new skill of user  into the database"
    )
    @PostMapping("/user/{userId}")
    public ResponseEntity<SkillsDto> addSkill(@RequestBody SkillsDto skillsDto,@PathVariable String  userId){

        SkillsDto savedSkillDto = skillsService.addSkill(skillsDto,userId);

        return  new ResponseEntity<>(savedSkillDto, HttpStatus.CREATED);


    }




    @Operation(
            summary = "Delete Skill RestApi"
            ,description = "This is used to delte the  skill of user  from the database"
    )
    @DeleteMapping("/id/{skillId}/user/{userId}")

    public ResponseEntity<ApiResponse>  deleteSkill(@PathVariable String skillId ,@PathVariable String  userId){

        skillsService.deleteSkillOfUser(skillId,userId);

        ApiResponse apiResponse = ApiResponse.builder().sucess(true)
                .mesaage("skill deleted successfully!").status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }

    @Operation(
            summary = "Get All Skill Of The User  RestApi"
            ,description = "This is used to fetch all   the skill of the user"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillsDto>>  getAllSkillsOfUser(@PathVariable String  userId){


       List<SkillsDto> skillItemDtos= skillsService.getAllSkillsOfUser(userId);

       return  new ResponseEntity<>(skillItemDtos,HttpStatus.OK);
    }









}
