package com.profileForge.dtos;


import com.profileForge.models.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ProjectDto {

    @Id
    private  String projectId;

    private  String  projectName;
    private  String   description;

    private Date startDate;
    private  Date endDate;

    private  int teamSize;

    private String projectImage;

    private String gitHubUrl;
    private  String  deployLink;
    private  String  demoVideo;



}
