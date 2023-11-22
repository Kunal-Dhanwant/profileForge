package com.profileForge.dtos;

import com.profileForge.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class SkillsDto {

    private  String id;
    private String  skillName;
    private int rating;



}