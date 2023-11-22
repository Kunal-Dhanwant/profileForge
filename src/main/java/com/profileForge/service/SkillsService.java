package com.profileForge.service;

import com.profileForge.dtos.SkillsDto;

import java.util.List;

public interface SkillsService {




     SkillsDto addSkill(SkillsDto skillsDto,String userId);

    // delete   skill

     void deleteSkillOfUser(String skillId, String  userId);





    List<SkillsDto>  getAllSkillsOfUser(String  userId);



}
