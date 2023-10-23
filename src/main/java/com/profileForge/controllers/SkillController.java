package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.SkillItemDto;
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
            ,description = "This is used to add the new skill  into the database"
    )
    @PostMapping
    public ResponseEntity<SkillsDto> addSkill(@RequestBody SkillsDto skillsDto){

        SkillsDto savedSkillDto = skillsService.addSkill(skillsDto);

        return  new ResponseEntity<>(savedSkillDto, HttpStatus.CREATED);


    }
    @Operation(
            summary = "Update Skill RestApi"
            ,description = "This is used to update  the  skill  into the database"
    )


    @PutMapping("/id/{skillId}")
    public ResponseEntity<SkillsDto> addSkill(@RequestBody SkillsDto skillsDto, @PathVariable String  skillId){

        SkillsDto updateskill = skillsService.updateskill(skillsDto,skillId);

        return  new ResponseEntity<>(updateskill, HttpStatus.OK);


    }
    @Operation(
            summary = "Delete Skill RestApi"
            ,description = "This is used to delte the  skill  from the database"
    )
    @DeleteMapping("/id/{skillId}")

    public ResponseEntity<ApiResponse>  deleteSkill(@PathVariable String skillId ){

        skillsService.deleteskill(skillId);

        ApiResponse apiResponse = ApiResponse.builder().sucess(true)
                .mesaage("skill deleted successfully!").status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }
    @Operation(
            summary = "Get All Skill RestApi"
            ,description = "This is used to  fetch all the skills present in  the database"
    )

    @GetMapping()
    public ResponseEntity<List<SkillsDto>> getAllSkill(){

        List<SkillsDto>  skillsDtos =  skillsService.getAllSkill();
        return  new ResponseEntity<>(skillsDtos,HttpStatus.OK);

    }
    @Operation(
            summary = "Get All Key Skill  RestApi"
            ,description = "This is used to  fetch  all key skill from the database"
    )

    @GetMapping("/{key}")
    public ResponseEntity<List<SkillsDto>> getAllSkillByKey(@PathVariable String  key){

        List<SkillsDto>  skillsDtos =  skillsService.getSkillsByKey(key);
        return  new ResponseEntity<>(skillsDtos,HttpStatus.OK);

    }
    @Operation(
            summary = "Assign Skill To User  RestApi"
            ,description = "This is used to  assign the skill to the user"
    )

    //  assign skill to the user

    @PostMapping("/id/{skillId}/user/{userId}")

    public ResponseEntity<ApiResponse>  assignSkill(@PathVariable String userId,@PathVariable String skillId ){

        skillsService.AddSkillToUser(userId,skillId);

        ApiResponse apiResponse = ApiResponse.builder().sucess(true)
                .mesaage("skill added successfully!").status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }
    @Operation(
            summary = "Delete Skill Of The User  RestApi"
            ,description = "This is used to delte  the skill of the user"
    )

    @DeleteMapping("/id/{skillId}/user/{userId}")
    public  ResponseEntity<ApiResponse> delteSkill(@PathVariable String userId,@PathVariable String skillId){

        skillsService.deleteSkillOfUser(userId,skillId);
        ApiResponse apiResponse = ApiResponse.builder().sucess(true)
                .mesaage("skill deleted successfully!").status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @Operation(
            summary = "Get All Skill Of The User  RestApi"
            ,description = "This is used to fetch all   the skill of the user"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillItemDto>>  getAllSkillsOfUser(@PathVariable String  userId){


       List<SkillItemDto> skillItemDtos= skillsService.getAllSkillsOfUser(userId);

       return  new ResponseEntity<>(skillItemDtos,HttpStatus.OK);
    }









}
