package com.profileForge.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder


@Entity
@Table(name = "PROJECT")
public class Project {


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

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}
