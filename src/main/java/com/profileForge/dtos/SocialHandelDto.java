package com.profileForge.dtos;

import jakarta.persistence.*;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SocialHandelDto
{


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



}
