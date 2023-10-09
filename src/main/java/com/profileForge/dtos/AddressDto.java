package com.profileForge.dtos;


import com.profileForge.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddressDto {

    private int id;
    private  String city;
    private  String  state;
    private  String  country;
    private String pincode;



}
