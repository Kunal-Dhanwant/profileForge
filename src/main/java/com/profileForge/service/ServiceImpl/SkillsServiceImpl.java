package com.profileForge.service.ServiceImpl;


import com.profileForge.dtos.SkillItemDto;
import com.profileForge.dtos.SkillsDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Skills;
import com.profileForge.models.SkillsItem;
import com.profileForge.models.User;
import com.profileForge.repos.SkillItemsRepo;
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

    @Autowired
    private SkillItemsRepo skillItemsRepo;

    @Override
    public SkillsDto addSkill(SkillsDto skillsDto) {

        String  id = UUID.randomUUID().toString();
        skillsDto.setId(id);

        Skills skills = mapper.map(skillsDto,Skills.class);

        Skills savedskill = skillsRepo.save(skills);

        return  mapper.map(savedskill,SkillsDto.class);
    }

    @Override
    public void deleteskill(String id) {

        Skills skills = skillsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("skill  with given id doest not exist"));

        skillsRepo.delete(skills);

    }

    @Override
    public SkillsDto updateskill(SkillsDto skillsDto, String id) {

        Skills skills = skillsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("skill  with given id doest not exist"));

        skills.setSkillName(skillsDto.getSkillName());
        skills.setSkillLogoUrl(skillsDto.getSkillLogoUrl());

     Skills updatedSkill =    skillsRepo.save(skills);

        return mapper.map(updatedSkill,SkillsDto.class);
    }

    @Override
    public List<SkillsDto> getAllSkill() {

        List<Skills> skills = skillsRepo.findAll();

        List<SkillsDto>  skillsDtos =  skills.stream().map(skills1 -> mapper.map(skills1,SkillsDto.class)).collect(Collectors.toList());


        return skillsDtos;
    }

    @Override
    public List<SkillsDto> getSkillsByKey(String key) {
        List<Skills> skills = skillsRepo.findBySkillNameStartsWith(key);

        List<SkillsDto>  skillsDtos =  skills.stream().map(skills1 -> mapper.map(skills1,SkillsDto.class)).collect(Collectors.toList());


        return skillsDtos;


    }

    @Override
    public void AddSkillToUser(String userId, String skillId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        Skills skills = skillsRepo.findById(skillId).orElseThrow(()-> new ResourceNotFoundException("skill  with given id doest not exist"));

        SkillsItem skillsItem = new SkillsItem();
        skillsItem.setSkills(skills);
        skillsItem.setUser(user);


        user.getSkillsList().add(skillsItem);
        userRepository.save(user);

        skillItemsRepo.save(skillsItem);








    }

    @Override
    public void deleteSkillOfUser(String userId, String skillId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        Skills skills = skillsRepo.findById(skillId).orElseThrow(()-> new ResourceNotFoundException("skill  with given id doest not exist"));

        SkillsItem skillsItem = skillItemsRepo.findByUserAndSkills(user,skills);

        user.getSkillsList().remove(skillsItem);
        userRepository.save(user);
        skillItemsRepo.delete(skillsItem);




    }

    @Override
    public List<SkillItemDto> getAllSkillsOfUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));

        List<SkillsItem> skillsItems = skillItemsRepo.findAllSkillsItemByUser(user);

        log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        for (SkillsItem s: skillsItems
             ) {
            log.info(s.getId()+"");
            log.info(s.getSkills().getId());
            log.info(s.getSkills().getSkillName());
            log.info(s.getSkills().getSkillLogoUrl());

        }




        List<SkillItemDto> skillItemDtos = skillsItems.stream().map(skillsItem -> mapper.map(skillsItem,SkillItemDto.class)).collect(Collectors.toList());


        return  skillItemDtos;

    }
}
