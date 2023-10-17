package com.profileForge.dtos;

import com.profileForge.models.Skills;
import com.profileForge.models.User;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SkillItemDto {

    private int id;
    private SkillsDto skills;



}
