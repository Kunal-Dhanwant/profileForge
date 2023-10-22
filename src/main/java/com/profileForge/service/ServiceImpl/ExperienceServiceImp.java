package com.profileForge.service.ServiceImpl;

import com.profileForge.dtos.ExperienceDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Experience;
import com.profileForge.models.User;
import com.profileForge.repos.ExperienceRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.ExperienceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ExperienceServiceImp implements ExperienceService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ExperienceRepo experienceRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExperienceDto addExperience(String userId, ExperienceDto experienceDto) {

        String  id = UUID.randomUUID().toString();
        experienceDto.setId(id);


        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id does not exist"));

        Experience experience = mapper.map(experienceDto,Experience.class);
        user.getExperiences().add(experience);
        userRepository.save(user);
        experience.setUser(user);
        Experience savedExperience=  experienceRepo.save(experience);

        return mapper.map(savedExperience,ExperienceDto.class);


    }

    @Override
    public void deleteExperince(String userId, String experienceId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id does not exist"));

        Experience experience = experienceRepo.findById(experienceId).orElseThrow(()-> new ResourceNotFoundException("experience with given id does not exist"));

        user.getExperiences().remove(experience);
        userRepository.save(user);
        experienceRepo.delete(experience);

    }

    @Override
    public List<ExperienceDto> getAllExperience(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id does not exist"));

        List<Experience> experienceList = experienceRepo.findAllByUser(user);

        List<ExperienceDto> experienceDtoList = experienceList.stream().map(experience -> mapper.map(experience,ExperienceDto.class))
                .collect(Collectors.toList());

        return experienceDtoList;
    }

    @Override
    public ExperienceDto updateExperience(ExperienceDto experienceDto, String experienceId) {
        Experience experience = experienceRepo.findById(experienceId).orElseThrow(()-> new ResourceNotFoundException("experience with given id does not exist"));

        experience.setDescription(experienceDto.getDescription());
        experience.setEndDate(experienceDto.getEndDate());
        experience.setStartDate(experienceDto.getStartDate());
        experience.setOrganizationName(experienceDto.getOrganizationName());
        experience.setPosition(experienceDto.getPosition());
       Experience experience1= experienceRepo.save(experience);


        return mapper.map(experience1,ExperienceDto.class) ;
    }
}
