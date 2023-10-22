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


@Entity
@Table(name = "USER")
public class User {
    @Id
    private String userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = true)
    private String userName;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    private String firstName;
    private String lastName;
    @Column(length = 250)
    private String bio;
    private String phoneNo;

    private String profileImage;
    private  String profileImageUrl;

    private  String ResumeFile;
    private  String  resuleFileUrl;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")

    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Education> educations = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private SocialHandel socialHandel;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<SkillsItem> skillsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Experience> experiences = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Project> projects = new ArrayList<>();


}
