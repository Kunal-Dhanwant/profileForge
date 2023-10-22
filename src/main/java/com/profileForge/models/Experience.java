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
@Table(name ="Experience")
public class Experience {

    @Id
    private  String id;
    @Column(nullable = false)
    private String organizationName;
    private  String  position;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private  Date endDate;
    private  String description;

    private String  organisationLogoUrl;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}
