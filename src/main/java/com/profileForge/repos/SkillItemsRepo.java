package com.profileForge.repos;


import com.profileForge.models.Skills;
import com.profileForge.models.SkillsItem;
import com.profileForge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillItemsRepo  extends JpaRepository<SkillsItem ,Integer> {


    SkillsItem findByUserAndSkills(User user,Skills skills);

    List<SkillsItem>  findAllSkillsItemByUser(User user);
}
