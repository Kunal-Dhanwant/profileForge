package com.profileForge.models;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String city;
    private  String  state;
    private  String  country;
    private String pincode;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;





}
