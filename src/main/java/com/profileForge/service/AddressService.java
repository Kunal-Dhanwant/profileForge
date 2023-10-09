package com.profileForge.service;

import com.profileForge.dtos.AddressDto;

public interface AddressService {


    //  create Address of user

    AddressDto createAddress(AddressDto addressDto,String  userId);

    //  update  addreess of user
    AddressDto updateAddress(AddressDto addressDto,String userId);



}
