package com.profileForge.dtos;


import com.profileForge.models.Education;
import jakarta.persistence.Id;
import lombok.*;

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
   private    String userName;
   private String password;
   private String firstName;
   private String lastName;
   private   String bio;
   private String phoneNo;
   private AddressDto address;

   private SocialHandelDto socialHandel;

 private List<EducationDto> educations;

}
