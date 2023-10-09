package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.SignUpDto;
import com.profileForge.dtos.UserDto;
import com.profileForge.models.User;
import com.profileForge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllers {


  @Autowired
  private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto>  createUser(@RequestBody SignUpDto signUpDto){

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
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable String userId){

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






}
