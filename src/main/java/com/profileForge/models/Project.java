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

    @Column(nullable = false)
    private  String  projectName;
    @Column(nullable = false)
    private  String   description;

    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
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
