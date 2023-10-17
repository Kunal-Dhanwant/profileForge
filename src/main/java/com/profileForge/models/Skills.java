package com.profileForge.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "SKILLS")
public class Skills {

    @Id
    private String id;

    private String skillName;
    private  String skillLogoUrl;



}
