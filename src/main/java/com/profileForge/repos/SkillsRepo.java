package com.profileForge.repos;


import com.profileForge.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepo extends JpaRepository<Skill,String > {



}

