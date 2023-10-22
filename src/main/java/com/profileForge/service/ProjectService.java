package com.profileForge.service;

import com.profileForge.dtos.ProjectDto;

import java.util.List;

public interface ProjectService {


    //  add  new project of the user

    ProjectDto addProject(String  userId,ProjectDto projectDto);


    //  update project;

    ProjectDto updateProject(ProjectDto projectDto,String prjectId);

    //  delete project

    void deleteProject(String  userId,String  projectId);


    //  fetch all the project of user

    List<ProjectDto> getAllProjects(String userId);

}
