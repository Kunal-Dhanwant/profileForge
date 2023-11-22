package com.profileForge.models;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    private  String id;
    private String  skillName;
    private int rating;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
