package com.profileForge.dtos;

import com.profileForge.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class ExperienceDto {


    private  String id;

    private String organizationName;
    private  String  position;
    private Date startDate;
    private  Date endDate;
    private  String description;
    private String  organisationLogoUrl;



}
