package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.SocialHandelDto;
import com.profileForge.models.SocialHandel;
import com.profileForge.service.SocialHandelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Social Handels CRUD RestApi"
)
@RestController
@RequestMapping("social")
public class SocialHandelController {

    @Autowired
    private SocialHandelService socialHandelService;



    @Operation(

    )
    @PostMapping("/user/{userId}")

    public ResponseEntity<SocialHandelDto> addSocailHandels(@RequestBody SocialHandelDto socialHandelDto, @PathVariable String  userId){


        SocialHandelDto socialHandelDto1 = socialHandelService.addSocialHandel(socialHandelDto,userId);

        return  new ResponseEntity<>(socialHandelDto1, HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}")

    public ResponseEntity<SocialHandelDto> updateSocailHandels(@RequestBody SocialHandelDto socialHandelDto, @PathVariable String  userId){


        SocialHandelDto socialHandelDto1 = socialHandelService.updateSocialHandel(socialHandelDto,userId);

        return  new ResponseEntity<>(socialHandelDto1, HttpStatus.OK);
    }


    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> deleteSocial( @PathVariable String  userId){


     socialHandelService.deleteSocialHandel(userId);

     ApiResponse apiResponse = ApiResponse.builder()
             .mesaage("Social Handels deleted SuccessFully!")
             .sucess(true).status(HttpStatus.OK).build();


        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}")
    public  ResponseEntity<SocialHandelDto> getAllHandels(@PathVariable String  userId){

        SocialHandelDto socialHandelDto = socialHandelService.getSocialHandel(userId);
        return  new ResponseEntity<>(socialHandelDto, HttpStatus.OK);

    }




}
