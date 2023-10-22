package com.profileForge.controllers;

import com.profileForge.dtos.AchievementDto;
import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.FileResponse;
import com.profileForge.dtos.UserDto;
import com.profileForge.service.AchievementService;
import com.profileForge.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/achievement")
public class AchievementController {


    @Value("${user.certificate.file.path}")
    private String certificateUploadPath;
    @Autowired
    private AchievementService achievementService;

    @Autowired
    private FileService fileService;



    @PostMapping("/user/{userId}")
    public ResponseEntity<AchievementDto> addAchievement(@RequestBody AchievementDto achievementDto,@PathVariable String userId){

        AchievementDto savedAchievement = achievementService.addAchievement(userId,achievementDto);

        return  new ResponseEntity<>(savedAchievement, HttpStatus.CREATED);

    }

    @DeleteMapping("/id/{achievementId}/user/{userId}")
    private ResponseEntity<ApiResponse> deleteAchievement(@PathVariable String achievementId, @PathVariable String userId){

        achievementService.deleteAchievement(userId,achievementId);

        ApiResponse apiResponse = ApiResponse.builder()
                .sucess(true).mesaage("deleted Successfully!")
                .status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);



    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AchievementDto>>    getAll(@PathVariable String  userId){

        List<AchievementDto> achievementDtoList = achievementService.getAllAchievements(userId);

        return  new ResponseEntity<>(achievementDtoList,HttpStatus.OK);

    }

    @PutMapping("/id/{achievementId}")
    public ResponseEntity<AchievementDto> updateAchievement(@RequestBody AchievementDto achievementDto,@PathVariable String achievementId){

        AchievementDto savedAchievement = achievementService.updateAchievement(achievementDto,achievementId);

        return  new ResponseEntity<>(savedAchievement, HttpStatus.CREATED);

    }














}
