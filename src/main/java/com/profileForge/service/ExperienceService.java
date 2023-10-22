package com.profileForge.service;

import com.profileForge.dtos.ExperienceDto;

import java.util.List;

public interface ExperienceService {


    //  add  new experince of user
    ExperienceDto addExperience( String  userId,ExperienceDto experienceDto);


    // delete  experince of user
    void   deleteExperince(String  userId,String experienceId);

    //  get all  Experience of  given user by id

    List<ExperienceDto>  getAllExperience(String  userId);

    //  update userExperience
    ExperienceDto updateExperience(ExperienceDto experienceDto,String experienceId);


}
