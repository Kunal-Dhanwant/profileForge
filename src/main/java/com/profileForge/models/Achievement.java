package com.profileForge.models;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder


@Entity
@Table(name = "ACHIEVEMENTS")
public class Achievement {

    @Id
    private  String id;


    private  String CerificateName;
    @Column(nullable = false)
    private  String description;

    @Column(nullable = false)
    private String  Organisation;

    private String certificateFile;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}
