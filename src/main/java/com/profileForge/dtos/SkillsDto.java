package com.profileForge.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SkillsDto {

    private String id;
    private String skillName;
    private  String skillLogoUrl;
}
