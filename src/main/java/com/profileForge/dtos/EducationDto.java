package com.profileForge.dtos;

import jakarta.persistence.Id;
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

    private String instituteName;
    private String City;
    private  String Country;
    private Date startDate;
    private Date endDate;

    private String Degree;
    private String field;

    private int GPA;
}
