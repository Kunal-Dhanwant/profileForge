package com.profileForge.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.profileForge.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "please provide organizationname")

    private String organizationName;
    private  String  position;
    @NotBlank(message = "please provide starting Date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;
    @NotBlank(message = "please provide edning Date")

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private  Date endDate;

    @NotBlank(message = "please provide description")
    private  String description;

    private String  organisationLogoUrl;



}
