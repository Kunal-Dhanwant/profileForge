package com.profileForge.controllers;


import com.profileForge.dtos.AddressDto;
import com.profileForge.dtos.ApiResponse;
import com.profileForge.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping("user/{userId}")
    public ResponseEntity<AddressDto>  createUserAddress(@RequestBody AddressDto addressDto, @PathVariable String userId){

        AddressDto addressDto1 = addressService.createAddress(addressDto, userId);

        return  new ResponseEntity<>(addressDto1, HttpStatus.CREATED);


    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<AddressDto>  updateUserAddress(@RequestBody AddressDto addressDto, @PathVariable String userId){

        AddressDto addressDto1 = addressService.updateAddress(addressDto, userId);

        return  new ResponseEntity<>(addressDto1, HttpStatus.CREATED);


    }

    @DeleteMapping("/user/{userId}")

    public  ResponseEntity<ApiResponse>  deleteAddress(@PathVariable String  userId){

        addressService.deleteAddressOfUser(userId);

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.OK)
                .mesaage("Address of user deleted success fully!")
                .sucess(true).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("user/{userId}")

    public  ResponseEntity<AddressDto>  findAddressOfUser(@PathVariable String  userId){

        AddressDto addressDto =addressService.getAddressOfUser(userId);

        return  new ResponseEntity<>(addressDto, HttpStatus.CREATED);

    }



}
