package com.profileForge.controllers;


import com.profileForge.dtos.AddressDto;
import com.profileForge.dtos.ApiResponse;
import com.profileForge.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Address CRUD RestApi"
)
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Operation(
            summary = " Create Address RestApi"
    )

    @PostMapping("user/{userId}")
    public ResponseEntity<AddressDto>  createUserAddress(@RequestBody AddressDto addressDto, @PathVariable String userId){

        AddressDto addressDto1 = addressService.createAddress(addressDto, userId);

        return  new ResponseEntity<>(addressDto1, HttpStatus.CREATED);


    }
    @Operation(
            summary = " Update Address RestApi"
    )

    @PutMapping("/user/{userId}")
    public ResponseEntity<AddressDto>  updateUserAddress(@RequestBody AddressDto addressDto, @PathVariable String userId){

        AddressDto addressDto1 = addressService.updateAddress(addressDto, userId);

        return  new ResponseEntity<>(addressDto1, HttpStatus.CREATED);


    }
    @Operation(
            summary = " Delete Address RestApi"
    )

    @DeleteMapping("/user/{userId}")

    public  ResponseEntity<ApiResponse>  deleteAddress(@PathVariable String  userId){

        addressService.deleteAddressOfUser(userId);

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.OK)
                .mesaage("Address of user deleted success fully!")
                .sucess(true).build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(
            summary = " Fetach All Addresses RestApi"
    )

    @GetMapping("user/{userId}")

    public  ResponseEntity<AddressDto>  findAddressOfUser(@PathVariable String  userId){

        AddressDto addressDto =addressService.getAddressOfUser(userId);

        return  new ResponseEntity<>(addressDto, HttpStatus.CREATED);

    }



}
