package com.profileForge.dtos;


import com.profileForge.models.User;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder



public class AchievementDto {


    private  String id;

    private  String CerificateName;
    private  String description;
    private String  Organisation;
    private String certificateFile;




}