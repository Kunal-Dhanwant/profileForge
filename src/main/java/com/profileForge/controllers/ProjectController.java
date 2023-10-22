package com.profileForge.controllers;


import com.profileForge.dtos.ApiResponse;
import com.profileForge.dtos.ProjectDto;
import com.profileForge.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    private ProjectService projectService;



    // add  new project of user
    @PostMapping("/user/{userId}")
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto, @PathVariable  String  userId){

        ProjectDto savedProject = projectService.addProject(userId,projectDto);

        return  new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }


    // delete project of user

    @DeleteMapping("/id/{projectId}/user/{userId}")
    private  ResponseEntity<ApiResponse> deleteproject(@PathVariable String  userId,@PathVariable String projectId){

        projectService.deleteProject(userId,projectId);

        ApiResponse apiResponse
                 = ApiResponse.builder().sucess(true)
                .status(HttpStatus.OK).mesaage("PROJECT DELETED SUCCESSFULLY!!!")
                .build();

        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @PutMapping("/id/{projectId}")

    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto, @PathVariable  String  projectId){

        ProjectDto savedProject = projectService.updateProject(projectDto,projectId);

        return  new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}")
    public  ResponseEntity<List<ProjectDto>> getAllProjectOfUser(@PathVariable String  userId){

        List<ProjectDto>  projectDtoList =  projectService.getAllProjects(userId);

        return  new ResponseEntity<>(projectDtoList,HttpStatus.OK);
    }



}
