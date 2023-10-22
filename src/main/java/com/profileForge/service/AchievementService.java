package com.profileForge.service;

import com.profileForge.dtos.AchievementDto;

import java.util.List;

public interface AchievementService {


    //  add new achievement of user
    AchievementDto addAchievement(String  userId,AchievementDto achievementDto);


    //  fetch all  achievement of user

    List<AchievementDto> getAllAchievements(String  userId);

    //  delet achievemnt of usert

    void  deleteAchievement(String  userId,String achievementId);

    // update achivement of user
    AchievementDto updateAchievement( AchievementDto achievementDto,String  achievementId);




}
