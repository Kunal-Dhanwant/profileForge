package com.profileForge.service;

import com.profileForge.dtos.SocialHandelDto;
import com.profileForge.models.SocialHandel;

public interface SocialHandelService {


    // add  socail handels of user

    SocialHandelDto addSocialHandel(SocialHandelDto socialHandelDto,String  userId);


    // update  social Handel Of User
    SocialHandelDto updateSocialHandel(SocialHandelDto socialHandelDto,String  userId);



    //  delet  socail Handel of user

    void deleteSocialHandel(String  userId);




    //  get socail handel  of  user

    SocialHandelDto getSocialHandel(String  userId);





}
