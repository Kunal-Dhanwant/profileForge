package com.profileForge.models;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Social")
public class SocialHandel
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int Id;

    private  String gmail;
    private  String linkedin;

    private  String x;

    private  String  thread;
    private  String  instagram;
    private String   leetCode;
    private  String  codechief;
    private  String  codeforce;
    private  String geeksForGeeks;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
