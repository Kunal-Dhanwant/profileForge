package com.profileForge.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "Education")

public class Education {

    @Id
    private  String  educationId;

    private String instituteName;
    private String City;
    private  String Country;
    private Date startDate;
    private Date endDate;

    private String Degree;
    private String field;

    private int GPA;


    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;



}
