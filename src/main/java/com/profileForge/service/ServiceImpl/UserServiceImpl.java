package com.profileForge.service.ServiceImpl;

import com.profileForge.dtos.SignUpDto;
import com.profileForge.dtos.UserDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.User;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDto createUser(SignUpDto signUpDto) {

        String userId=  UUID.randomUUID().toString();

        User user =modelMapper.map(signUpDto, User.class);
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);



    }


    //  update user

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id doest not exist"));

        log.info(user.getEmail() +" " + user.getUserUrl()+"this is user **********");



        user.setUserUrl(userDto.getUserUrl());
        user.setFirstName(userDto.getFirstName());

        user.setLastName(userDto.getLastName());
        user.setBio(userDto.getBio());
        user.setPhoneNo(userDto.getPhoneNo());


        User updateduser = userRepository.save(user);
        log.info(updateduser.getEmail() +" " + updateduser.getUserUrl()+"this is user **********");

        return  modelMapper.map(updateduser, UserDto.class);






    }


    // delete  user
    @Override
    public void deleteUser(String userId) {

        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id doest not exist"));

        userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();



        List<UserDto> dtolist = users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());



        return  dtolist;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id doest not exist"));

        log.info(user.getEmail() +" " + user.getUserUrl()+"this is user **********");

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByUserName(String username) {

        User user =  userRepository.findByUserUrl(username).orElseThrow(()-> new ResourceNotFoundException("User with given User Name doest not exist"));



        return modelMapper.map(user, UserDto.class) ;
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {

        User user =  userRepository.findByEmailAndPassword(email, password).orElseThrow(()-> new ResourceNotFoundException("User with given email  doest not exist"));

          return modelMapper.map(user, UserDto.class) ;
    }

    @Override
    public boolean isexist(String userName) {


        return userRepository.existsByUserUrl(userName);
    }

    @Override
    public UserDto updateProfileImage(UserDto userDto, String userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id doest not exist"));

        user.setProfileImage(userDto.getProfileImage());
        user.setProfileImageUrl(userDto.getProfileImageUrl());

        User user1 = userRepository.save(user);
        return  modelMapper.map(user1,UserDto.class);

    }

    @Override
    public UserDto updateUserResume(UserDto userDto, String userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id doest not exist"));

        user.setResumeFile(userDto.getResumeFile());
        user.setResuleFileUrl(userDto.getResuleFileUrl());

        User user1 = userRepository.save(user);
        return  modelMapper.map(user1,UserDto.class);


    }


}
