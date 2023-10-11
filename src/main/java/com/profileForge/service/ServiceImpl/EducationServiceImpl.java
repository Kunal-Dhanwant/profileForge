package com.profileForge.service.ServiceImpl;


import com.profileForge.dtos.EducationDto;
import com.profileForge.dtos.UserDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Education;
import com.profileForge.models.User;
import com.profileForge.repos.EducationRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EducationServiceImpl  implements EducationService {

    @Autowired
    private EducationRepo educationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public EducationDto addEducation(EducationDto educationDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given Id does not exist"));

        List<Education> educationList = user.getEducations();
        String educationId = UUID.randomUUID().toString();
        educationDto.setEducationId(educationId);

        Education education = modelMapper.map(educationDto, Education.class);

        educationList.add(education);

        user.setEducations(educationList);
        education.setUser(user);

        User user1 = userRepository.save(user);


        return  modelMapper.map(user1.getEducations().get(user1.getEducations().size()-1), EducationDto.class);
    }

    @Override
    public EducationDto updateEducation(EducationDto educationDto, String educationId) {

        Education  edu = educationRepo.findById(educationId).orElseThrow(()->new ResourceNotFoundException("Education with given id does not exist"));
        edu.setInstituteName(educationDto.getInstituteName());
        edu.setCity(educationDto.getCity());
        edu.setCountry(educationDto.getCountry());
        edu.setDegree(educationDto.getDegree());
        edu.setGPA(educationDto.getGPA());
        edu.setField(educationDto.getField());
        edu.setStartDate(educationDto.getStartDate());
        edu.setEndDate(educationDto.getEndDate());

       Education education= educationRepo.save(edu);

        return modelMapper.map(education, EducationDto.class);
    }




    @Override
    public void deleteEducation(String educationId,String  userId) {




        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given Id does not exist"));

        Education  education = educationRepo.findById(educationId).orElseThrow(()->new ResourceNotFoundException("Education with given id does not exist"));

        user.getEducations().remove(education);


        userRepository.save(user);


        educationRepo.delete(education);




    }



    @Override
    public List<EducationDto> getAllEducationOfUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given Id does not exist"));

        List<Education> educations = user.getEducations();

        List<EducationDto> dtolist = educations.stream().map(education-> new ModelMapper().map(education, EducationDto.class)).collect(Collectors.toList());

        return dtolist;
    }
}
