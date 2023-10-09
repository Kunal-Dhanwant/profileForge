package com.profileForge.models;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Table(name = "USER")
@Entity
public class User {

    @Id
  private   String userId;
 private    String email;

  private   String userName;
   private String password;
  private   String firstName;
  private   String lastName;
 private    String bio;
  private   String phoneNo;

 @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
 @JoinColumn(name = "address_id")
 private Address address;


}
