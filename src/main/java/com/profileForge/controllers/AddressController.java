package com.profileForge.controllers;


import com.profileForge.dtos.AddressDto;
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


    @PutMapping("/{userId}")
    public ResponseEntity<AddressDto>  createUserAddress(@RequestBody AddressDto addressDto, @PathVariable String userId){

        AddressDto addressDto1 = addressService.createAddress(addressDto, userId);

        return  new ResponseEntity<>(addressDto1, HttpStatus.CREATED);


    }



}
