package com.profileForge.repos;


import com.profileForge.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo  extends JpaRepository<Project,String> {
}
