package com.profileForge.service;

import com.profileForge.dtos.EducationDto;

import java.util.List;

public interface EducationService {


    //  add  education of user

    EducationDto addEducation(EducationDto educationDto,String  userId);



    //  update  education of user
    EducationDto updateEducation(EducationDto educationDto,String educationId);






    // delete  education of user;
    void deleteEducation(String  educationId,String  userId);




    //  get all  education of user

    List<EducationDto> getAllEducationOfUser(String userId);



}
