package com.profileForge.controllers;

import com.profileForge.Jwt.JwtHelper;
import com.profileForge.dtos.LoginRequest;
import com.profileForge.dtos.LoginResponse;
import com.profileForge.dtos.UserDto;
import com.profileForge.exception.ImageBadApiRequest;
import com.profileForge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(
        name = "Login Rest Api for the user"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;


    @Operation(
            summary = "Login Rest Api",
            description = "This is used to login the user"
    )

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        String token = this.helper.generateToken(userDetails);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        LoginResponse response = LoginResponse.builder()
                .jwtToken(token).user(userDto).build();

        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }






    private void doAuthenticate(String email ,String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        try {
            authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            // TODO: handle exception
            throw new ImageBadApiRequest("invalid username or password!!");
        }
    }



}
