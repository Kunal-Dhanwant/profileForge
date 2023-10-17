package com.profileForge.repos;


import com.profileForge.models.SkillsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillItemsRepo  extends JpaRepository<SkillsItem ,Integer> {
}
