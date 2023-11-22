package com.profileForge.service.ServiceImpl;


import com.profileForge.dtos.SkillsDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Skill;
import com.profileForge.models.User;
import com.profileForge.repos.SkillsRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.SkillsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SkillsServiceImpl implements SkillsService {


    @Autowired
    private SkillsRepo skillsRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;




    @Override
    public SkillsDto addSkill(SkillsDto skillsDto,String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));


        String  id = UUID.randomUUID().toString();
        skillsDto.setId(id);

        Skill skills = mapper.map(skillsDto,Skill.class);
        user.getSkillsList().add(skills);
        skills.setUser(user);
        userRepository.save(user);

        Skill savedskill = skillsRepo.save(skills);

        return  mapper.map(savedskill,SkillsDto.class);
    }




    @Override
    public void deleteSkillOfUser(String userId, String skillId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        Skill skills = skillsRepo.findById(skillId).orElseThrow(()-> new ResourceNotFoundException("skill  with given id doest not exist"));

         user.getSkillsList().remove(skills);
         userRepository.save(user);
          skillsRepo.delete(skills);




    }

    @Override
    public List<SkillsDto> getAllSkillsOfUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));

        List<Skill>  skillsItems = user.getSkillsList();

        log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");





        List<SkillsDto> skillItemDtos = skillsItems.stream().map(skillsItem -> mapper.map(skillsItem,SkillsDto.class)).collect(Collectors.toList());


        return  skillItemDtos;

    }
}
