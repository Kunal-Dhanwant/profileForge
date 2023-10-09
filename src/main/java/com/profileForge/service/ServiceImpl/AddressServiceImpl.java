package com.profileForge.service.ServiceImpl;


import com.profileForge.dtos.AddressDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Address;
import com.profileForge.models.User;
import com.profileForge.repos.AddressRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepository userRepository;
    @Override
    public AddressDto createAddress(AddressDto addressDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        Address address = modelMapper.map(addressDto, Address.class);

        user.setAddress(address);
        address.setUser(user);


        User updateduser= userRepository.save(user);



        return modelMapper.map(updateduser.getAddress(), AddressDto.class);
    }



    @Override
    public AddressDto updateAddress(AddressDto addressDto, String userId) {


        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        Address address = addressRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("address of the user doest not exist please add address"));

        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setPincode(addressDto.getPincode());
        address.setState(addressDto.getState());

        Address updatedAddress = addressRepo.save(address);

        return modelMapper.map(updatedAddress, AddressDto.class) ;
    }
}
