package com.profileForge.dtos;


import com.profileForge.models.Achievement;
import com.profileForge.models.Education;
import com.profileForge.models.Experience;
import com.profileForge.models.SkillsItem;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDto {

   private   String userId;
   private String email;

   @NotBlank(message = "please provide unique user name")
   private    String userName;
   private String password;
   @NotBlank(message = "please enter first name")
   private String firstName;
   private String lastName;

   @Size(max = 250,message = "bio should  have maxmimum 250 words")
   private   String bio;
   private String phoneNo;
   private String profileImage;
   private  String profileImageUrl;
   private  String ResumeFile;
   private  String  resuleFileUrl;

   private AddressDto address;

   private SocialHandelDto socialHandel;

   private List<EducationDto> educations;
   private List<SkillItemDto> skillsList = new ArrayList<>();

   private List<ExperienceDto> experiences = new ArrayList<>();

   private List<AchievementDto> achievements = new ArrayList<>();
   private List<ProjectDto> projects = new ArrayList<>();


}
