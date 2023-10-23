package com.profileForge.controllers;

import com.profileForge.dtos.AchievementDto;
import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.FileResponse;
import com.profileForge.dtos.UserDto;
import com.profileForge.service.AchievementService;
import com.profileForge.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Tag(
        name = "Achievements CRUD RestApi"
)
@RestController
@RequestMapping("/achievement")
public class AchievementController {


    @Value("${user.certificate.file.path}")
    private String certificateUploadPath;
    @Autowired
    private AchievementService achievementService;

    @Autowired
    private FileService fileService;

    @Operation(
            summary = "Add Achivement RestApi",
            description = "This is used to add the new achievement of the user"
    )

    @PostMapping("/user/{userId}")
    public ResponseEntity<AchievementDto> addAchievement(@RequestBody AchievementDto achievementDto,@PathVariable String userId){

        AchievementDto savedAchievement = achievementService.addAchievement(userId,achievementDto);

        return  new ResponseEntity<>(savedAchievement, HttpStatus.CREATED);

    }
    @Operation(
            summary = "Delete Achivement RestApi",
            description = "This is used to delte the  achievement of the user"
    )

    @DeleteMapping("/id/{achievementId}/user/{userId}")
    private ResponseEntity<ApiResponse> deleteAchievement(@PathVariable String achievementId, @PathVariable String userId){

        achievementService.deleteAchievement(userId,achievementId);

        ApiResponse apiResponse = ApiResponse.builder()
                .sucess(true).mesaage("deleted Successfully!")
                .status(HttpStatus.OK).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);



    }
    @Operation(
            summary = "Get All Achivements RestApi",
            description = "This is used to fetch all the achievements of the user"
    )


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AchievementDto>>    getAll(@PathVariable String  userId){

        List<AchievementDto> achievementDtoList = achievementService.getAllAchievements(userId);

        return  new ResponseEntity<>(achievementDtoList,HttpStatus.OK);

    }
    @Operation(
            summary = "Update Achivement RestApi",
            description = "This is used to update the achievement of the user"
    )

    @PutMapping("/id/{achievementId}")
    public ResponseEntity<AchievementDto> updateAchievement(@RequestBody AchievementDto achievementDto,@PathVariable String achievementId){

        AchievementDto savedAchievement = achievementService.updateAchievement(achievementDto,achievementId);

        return  new ResponseEntity<>(savedAchievement, HttpStatus.CREATED);

    }














}
