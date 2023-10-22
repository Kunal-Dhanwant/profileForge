package com.profileForge.models;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(nullable = false)
    private String instituteName;
    @Column(nullable = false)
    private String City;

    private  String Country;
    @Column(nullable = false)

    private Date startDate;
    @Column(nullable = false)

    private Date endDate;
    @Column(nullable = false)
    private String Degree;
    @Column(nullable = false)
    private String field;

    private int GPA;


    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;



}
