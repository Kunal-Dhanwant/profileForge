package com.profileForge.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.profileForge.models.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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


    @NotBlank(message = "please provide project name")
    private  String  projectName;

    @NotBlank(message = "please provide project description")
    private  String   description;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private  Date endDate;

    private  int teamSize;

    private String projectImage;

    private String gitHubUrl;
    private  String  deployLink;
    private  String  demoVideo;



}
