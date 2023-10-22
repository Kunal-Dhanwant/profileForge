package com.profileForge.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class EducationDto {



    private  String  educationId;

    @NotBlank(message = "please provide institute name")
    private String instituteName;
    @NotBlank(message = "please provide city name")
    private String City;
    @NotBlank(message = "please provide country name")
    private  String Country;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date endDate;
    @NotBlank(message = "please provide degree name")
    private String Degree;
    @NotBlank(message = "please provide field name")
    private String field;

    private int GPA;
}
