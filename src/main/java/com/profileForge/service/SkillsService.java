package com.profileForge.service;

import com.profileForge.dtos.SkillsDto;

import java.util.List;
import java.util.UUID;

public interface SkillsService {


    //  add new skills into database;

    SkillsDto addSkill(SkillsDto skillsDto);

    // delete   skill

     void deleteskill(String  id);


     //  update skill
    SkillsDto updateskill(SkillsDto skillsDto,String  id);

    // get all  skilss
    List<SkillsDto>   getAllSkill();


    ////fect  skill  by  key
    List<SkillsDto> getSkillsByKey(String  key);




    // assign  skill to  the  user
    void AddSkillToUser(String  userId,String skillId);


}
