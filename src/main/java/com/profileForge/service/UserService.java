package com.profileForge.service;

import com.profileForge.dtos.SignUpDto;
import com.profileForge.dtos.UserDto;
import com.profileForge.models.User;

import java.util.List;

public interface UserService {

    // create user
   UserDto createUser(SignUpDto signUpDto);

   //  update user
    UserDto updateUser(UserDto userDto,String userId);

    //delete  user
    void deleteUser(String  userid);

    //  getAll  users;
     List<UserDto> getAllUsers();

   //get user by id
     UserDto getUserById(String  userId);

    // get user by  user_name
    UserDto getUserByUserName(String userName);

    //  fetch user detail with email and password
    UserDto getUserByEmailAndPassword(String email,String password);


    boolean isexist(String userName);


    UserDto updateProfileImage(UserDto userDto, String userId);

    UserDto updateUserResume(UserDto userDto,String userId);












}
