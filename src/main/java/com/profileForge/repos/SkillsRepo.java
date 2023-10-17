package com.profileForge.repos;


import com.profileForge.models.Skills;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepo extends JpaRepository<Skills,String > {

    List<Skills>  findBySkillNameStartsWith(String skillName);

}

