package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.SkillsDto;
import com.profileForge.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillsService skillsService;


    @PostMapping
    public ResponseEntity<SkillsDto> addSkill(@RequestBody SkillsDto skillsDto){

        SkillsDto savedSkillDto = skillsService.addSkill(skillsDto);

        return  new ResponseEntity<>(savedSkillDto, HttpStatus.CREATED);


    }


    @PutMapping("/id/{skillId}")
    public ResponseEntity<SkillsDto> addSkill(@RequestBody SkillsDto skillsDto, @PathVariable String  skillId){

        SkillsDto updateskill = skillsService.updateskill(skillsDto,skillId);

        return  new ResponseEntity<>(updateskill, HttpStatus.OK);


    }

    @DeleteMapping("/id/{skillId}")

    public ResponseEntity<ApiResponse>  deleteSkill(@PathVariable String skillId ){

        skillsService.deleteskill(skillId);

        ApiResponse apiResponse = ApiResponse.builder().sucess(true)
                .mesaage("skill deleted successfully!").status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }

    @GetMapping()
    public ResponseEntity<List<SkillsDto>> getAllSkill(){

        List<SkillsDto>  skillsDtos =  skillsService.getAllSkill();
        return  new ResponseEntity<>(skillsDtos,HttpStatus.OK);

    }

    @GetMapping("/{key}")
    public ResponseEntity<List<SkillsDto>> getAllSkillByKey(@PathVariable String  key){

        List<SkillsDto>  skillsDtos =  skillsService.getSkillsByKey(key);
        return  new ResponseEntity<>(skillsDtos,HttpStatus.OK);

    }


    //  assign skill to the user

    @PostMapping("/id/{skillId}/user/{userId}")

    public ResponseEntity<ApiResponse>  assignSkill(@PathVariable String userId,@PathVariable String skillId ){

        skillsService.AddSkillToUser(userId,skillId);

        ApiResponse apiResponse = ApiResponse.builder().sucess(true)
                .mesaage("skill deleted successfully!").status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }









}
