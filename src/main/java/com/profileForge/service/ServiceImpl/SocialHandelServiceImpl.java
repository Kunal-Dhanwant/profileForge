package com.profileForge.service.ServiceImpl;

import com.profileForge.dtos.SocialHandelDto;
import com.profileForge.exception.ResourceNotFoundException;
import com.profileForge.models.SocialHandel;
import com.profileForge.models.User;
import com.profileForge.repos.SocialHandelRepo;
import com.profileForge.repos.UserRepository;
import com.profileForge.service.SocialHandelService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
@Slf4j
public class SocialHandelServiceImpl implements SocialHandelService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocialHandelRepo socialHandelRepo;
    @Override
    public SocialHandelDto addSocialHandel(SocialHandelDto socialHandelDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));


        SocialHandel socialHandel = modelMapper.map(socialHandelDto,SocialHandel.class);

        user.setSocialHandel(socialHandel);
        socialHandel.setUser(user);
        User saveduser = userRepository.save(user);


        return modelMapper.map(saveduser.getSocialHandel(), SocialHandelDto.class);
    }

    @Override
    public SocialHandelDto updateSocialHandel(SocialHandelDto socialHandelDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));

        SocialHandel socialHandel = user.getSocialHandel();

        socialHandel.setCodeforce(socialHandelDto.getCodeforce());
        socialHandel.setCodechief(socialHandelDto.getCodechief());
        socialHandel.setInstagram(socialHandelDto.getInstagram());
        socialHandel.setGeeksForGeeks(socialHandelDto.getGeeksForGeeks());
        socialHandel.setLeetCode(socialHandelDto.getLeetCode());

        socialHandel.setLinkedin(socialHandelDto.getLinkedin());
        socialHandel.setThread(socialHandelDto.getThread());

        socialHandel.setGmail(socialHandelDto.getGmail());
        socialHandel.setX(socialHandelDto.getX());
        user.setSocialHandel(socialHandel);
        User user1 = userRepository.save(user);
        return modelMapper.map(user1.getSocialHandel(),SocialHandelDto.class);
    }

    @Override
    public void deleteSocialHandel(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        SocialHandel socialHandel =  socialHandelRepo.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Social hanels of  the given user does not exist"));
        user.setSocialHandel(null);
        userRepository.save(user);
        socialHandelRepo.delete(socialHandel);


    }

    @Override
    public SocialHandelDto getSocialHandel(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id doest not exist"));
        SocialHandel socialHandel =  socialHandelRepo.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Social hanels of  the given user does not exist"));

        return modelMapper.map(socialHandel,SocialHandelDto.class);
    }
}
