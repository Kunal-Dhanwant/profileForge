package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.FileResponse;
import com.profileForge.dtos.SignUpDto;
import com.profileForge.dtos.UserDto;
import com.profileForge.models.User;
import com.profileForge.service.FileService;
import com.profileForge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(
        name = "CRUD REST APIs for User Resource"

)
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


    @Operation(
            summary = "Signup User REST API",
            description = "Signup User REST API is used to signUp the new  user "
    )

    @PostMapping
    public ResponseEntity<UserDto>  createUser( @Valid @RequestBody SignUpDto signUpDto){

        UserDto  saveduser =  userService.createUser(signUpDto);

        return new ResponseEntity<>(saveduser, HttpStatus.CREATED);


    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete the  user "
    )



    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>  deleteUser( @PathVariable String userId){
        userService.deleteUser(userId);

        ApiResponse apiResponse = ApiResponse.builder()
                .mesaage("user deleted successfully")
                .status(HttpStatus.OK)
                .sucess(true).build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update the given  user "
    )


    //  UPDATED USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable String userId){

        UserDto  updateUser =  userService.updateUser(userDto,userId);

        return new ResponseEntity<>(updateUser,HttpStatus.OK);


    }

    @Operation(
            summary = "Get  User REST API",
            description = "Get User REST API is used to fetch all  user "
    )


    //  get ALL users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto>  users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }
    @Operation(
            summary = "Fetch User REST API",
            description = "Fetch User REST API is used to fetch the detail of the user "
    )

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto>  getUserById(@PathVariable String userId){
        UserDto  userDto =  userService.getUserById(userId);

        return  new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @Operation(
            summary = "Fetch User REST API",
            description = "Fetch User REST API is used to fetch the detail of the user by userUrl "
    )

    @GetMapping("/username/{userUrl}")
    public ResponseEntity<UserDto>  getUserByUserName(@PathVariable String userUrl){
        UserDto  userDto =  userService.getUserByUserName(userUrl);

        return  new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @Operation(
            summary = "Exist User REST API",
            description = "Exist User REST API is used to check weather the given userUrl is already exost or not ? "
    )


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


    @Operation(
            summary = "upload User Profile Image REST API",
            description = "Upload User REST API is used to upload the profile image of  the  of the user"

    )

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

        userService.updateProfileImage(user, userId);

        FileResponse imageResponse = FileResponse.builder()
                .fileName(image.getOriginalFilename()).success(true)
                .message("Uploaded successfully "+ image.getOriginalFilename())
                .status(HttpStatus.CREATED).build();

        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);


    }

    @Operation(
            summary = "Fetch User Profile Image REST API",
            description = "This REST API is used to fetch the profile Image of the user "
    )

    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {



        UserDto user = userService.getUserById(userId);

        InputStream resource = fileService.getResources(imageUploadPath, user.getProfileImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());
    }

    @Operation(
            summary = "Upload User Resume REST API",
            description = "This User REST API is used to uplaod the resume of the user "
    )


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

        userService.updateUserResume(user, userId);

        FileResponse imageResponse = FileResponse.builder()
                .fileName(resume.getOriginalFilename()).success(true)
                .message("Uploaded successfully "+ resume.getOriginalFilename())
                .status(HttpStatus.CREATED).build();

        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);


    }
    @Operation(
            summary = "Fetch User Resume REST API",
            description = "This User REST API is used to fetch the resume of the user "
    )
    @GetMapping("/resume/{userId}")
    public void serveUserResume(@PathVariable String userId, HttpServletResponse response) throws IOException {



        UserDto user = userService.getUserById(userId);

        InputStream resource = fileService.getResources(resumeUploadPath, user.getResumeFile());
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());
    }










}
