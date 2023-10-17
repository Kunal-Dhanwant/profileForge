package com.profileForge.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "Skill_Item")
public class SkillsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private Skills skills;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
