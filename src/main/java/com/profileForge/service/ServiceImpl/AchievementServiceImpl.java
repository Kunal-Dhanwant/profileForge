package com.profileForge.service.ServiceImpl;


import com.profileForge.dtos.AchievementDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.Achievement;
import com.profileForge.models.User;
import com.profileForge.repos.AchievementRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.AchievementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl  implements AchievementService {

    @Autowired
    private AchievementRepo achievementRepo;

    @Autowired
    private ModelMapper mapper;


    @Autowired
    private UserRepository userRepository;

    @Override
    public AchievementDto addAchievement(String userId, AchievementDto achievementDto) {

        String  id = UUID.randomUUID().toString();
        achievementDto.setId(id);

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));

        Achievement achievement = mapper.map(achievementDto,Achievement.class);

        user.getAchievements().add(achievement);
        achievement.setUser(user);
        userRepository.save(user);
          Achievement savedAchievement = achievementRepo.save(achievement);



        return  mapper.map(savedAchievement,AchievementDto.class);
    }

    @Override
    public List<AchievementDto> getAllAchievements(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));

        List<Achievement> achievements = user.getAchievements();

        List<AchievementDto> achievementDtos= achievements.stream().map(achievement -> mapper.map(achievement,AchievementDto.class))
                .collect(Collectors.toList());

        return achievementDtos;
    }

    @Override
    public void deleteAchievement(String userId, String achievementId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));

        Achievement achievement = achievementRepo.findById(achievementId).orElseThrow(()->new ResourceNotFoundException("achievement with given id doest not exist"));
        user.getAchievements().remove(achievement);

        userRepository.save(user);
        achievementRepo.delete(achievement);


    }

    @Override
    public AchievementDto updateAchievement( AchievementDto achievementDto, String achievementId) {

        Achievement achievement = achievementRepo.findById(achievementId).orElseThrow(()->new ResourceNotFoundException("achievement with given id doest not exist"));


        achievement.setOrganisation(achievementDto.getOrganisation());
        achievement.setDescription(achievementDto.getDescription());
        achievement.setCerificateName(achievementDto.getCerificateName());
        achievement.setCertificateFile(achievementDto.getCertificateFile());

        Achievement achievement1 = achievementRepo.save(achievement);

        return mapper.map(achievement1,AchievementDto.class);
    }
}
