package com.profileForge.models;


import com.profileForge.dtos.SkillItemDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Table(name = "USER")
@Entity
public class User {

    @Id
   private   String userId;
   private    String email;

  private   String userName;
   private String password;
  private   String firstName;
  private   String lastName;
 private    String bio;
  private   String phoneNo;

 @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")

 private Address address;

 @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
 private List<Education> educations = new ArrayList<>();


 @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
 private  SocialHandel socialHandel;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private List<SkillsItem> skillsList = new ArrayList<>();


}
