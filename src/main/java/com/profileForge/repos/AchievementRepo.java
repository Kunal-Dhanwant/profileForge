package com.profileForge.repos;


import com.profileForge.models.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepo  extends JpaRepository<Achievement,String> {


}
