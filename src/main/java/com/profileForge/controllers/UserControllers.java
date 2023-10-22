package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.FileResponse;
import com.profileForge.dtos.SignUpDto;
import com.profileForge.dtos.UserDto;
import com.profileForge.models.User;
import com.profileForge.service.FileService;
import com.profileForge.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserControllers {


    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    @Value("${user.resume.file.path}")
    private String resumeUploadPath;


  @Autowired
  private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto>  createUser( @Valid @RequestBody SignUpDto signUpDto){

        UserDto  saveduser =  userService.createUser(signUpDto);

        return new ResponseEntity<>(saveduser, HttpStatus.CREATED);


    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>  deleteUser( @PathVariable String userId){
        userService.deleteUser(userId);

        ApiResponse apiResponse = ApiResponse.builder()
                .mesaage("user deleted successfully")
                .status(HttpStatus.OK)
                .sucess(true).build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }




    //  UPDATED USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable String userId){

        UserDto  updateUser =  userService.updateUser(userDto,userId);

        return new ResponseEntity<>(updateUser,HttpStatus.OK);


    }

    //  get ALL users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto>  users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }


    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto>  getUserById(@PathVariable String userId){
        UserDto  userDto =  userService.getUserById(userId);

        return  new ResponseEntity<>(userDto,HttpStatus.FOUND);
    }


    @GetMapping("/{userName}")
    public ResponseEntity<UserDto>  getUserByUserName(@PathVariable String userName){
        UserDto  userDto =  userService.getUserByUserName(userName);

        return  new ResponseEntity<>(userDto,HttpStatus.FOUND);
    }


    @GetMapping("/exist/{userName}")
    public  ResponseEntity<ApiResponse> doesUserExist(@PathVariable String userName){

        boolean  isexist = userService.isexist(userName);

        ApiResponse apiResponse;

        if(isexist){
            apiResponse = ApiResponse.builder().sucess(true)
                    .status(HttpStatus.OK).mesaage("Found").build();

        }else{
            apiResponse = ApiResponse.builder().sucess(true)
                    .status(HttpStatus.OK).mesaage("NotFound").build();

        }

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);



    }


    @PostMapping("/image/{userId}")
    public ResponseEntity<FileResponse> uploadUserImage(@RequestParam ("userImage") MultipartFile image, @PathVariable String userId) throws IOException {


        String imageName = fileService.uploadImage(image, imageUploadPath);

        UserDto  user = userService.getUserById(userId);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user")
                .path("/image/")
                .path(userId)

                .toUriString();

        log.info("&&&&&&&&&" + fileDownloadUri);
        user.setProfileImage(imageName);
        user.setProfileImageUrl(fileDownloadUri);

        userService.updateUser(user, userId);

        FileResponse imageResponse = FileResponse.builder()
                .fileName(image.getOriginalFilename()).success(true)
                .message("Uploaded successfully "+ image.getOriginalFilename())
                .status(HttpStatus.CREATED).build();

        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);


    }

    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {



        UserDto user = userService.getUserById(userId);

        InputStream resource = fileService.getResources(imageUploadPath, user.getProfileImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());
    }




    @PostMapping("/resume/{userId}")
    public ResponseEntity<FileResponse> uploadUserResume(@RequestParam ("userResume") MultipartFile resume, @PathVariable String userId) throws IOException {


        String resumeName = fileService.uploadImage(resume, resumeUploadPath);

        UserDto  user = userService.getUserById(userId);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user")
                .path("/resume/")
                .path(userId)

                .toUriString();

        log.info("&&&&&&&&&" + fileDownloadUri);


        user.setResumeFile(resumeName);
        user.setResuleFileUrl(fileDownloadUri);

        userService.updateUser(user, userId);

        FileResponse imageResponse = FileResponse.builder()
                .fileName(resume.getOriginalFilename()).success(true)
                .message("Uploaded successfully "+ resume.getOriginalFilename())
                .status(HttpStatus.CREATED).build();

        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);


    }

    @GetMapping("/resume/{userId}")
    public void serveUserResume(@PathVariable String userId, HttpServletResponse response) throws IOException {



        UserDto user = userService.getUserById(userId);

        InputStream resource = fileService.getResources(resumeUploadPath, user.getResumeFile());
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());
    }










}
