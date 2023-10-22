package com.profileForge.service.ServiceImpl;

import com.profileForge.dtos.ProjectDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Project;
import com.profileForge.models.User;
import com.profileForge.repos.ProjectRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public ProjectDto addProject(String userId, ProjectDto projectDto) {

        User user  = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id does not exist"));
        String  id = UUID.randomUUID().toString();
        projectDto.setProjectId(id);

        Project project = mapper.map(projectDto,Project.class);
        user.getProjects().add(project);
        project.setUser(user);

        userRepository.save(user);



        Project savedProject = projectRepo.save(project);


        return mapper.map(savedProject,ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto, String projectId) {

        Project project = projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("project with given id does not exist"));

        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setDemoVideo(projectDto.getDemoVideo());
        project.setDeployLink(projectDto.getDeployLink());
        project.setEndDate(projectDto.getEndDate());
        project.setStartDate(project.getStartDate());
        project.setGitHubUrl(projectDto.getGitHubUrl());
        project.setTeamSize(projectDto.getTeamSize());

        Project updateproject=  projectRepo.save(project);
        return mapper.map(updateproject,ProjectDto.class);
    }


    @Override
    public void deleteProject(String userId, String projectId) {
        User user  = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id does not exist"));
        Project project = projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("project with given id does not exist"));

        user.getProjects().remove(project);
        userRepository.save(user);
        projectRepo.delete(project);

    }

    @Override
    public List<ProjectDto> getAllProjects(String userId) {
        User user  = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id does not exist"));

        List<Project> projects = user.getProjects();

        List<ProjectDto> projectDtoList = projects.stream().map(project -> mapper.map(project,ProjectDto.class))
                .collect(Collectors.toList());

        return projectDtoList;
    }
}
