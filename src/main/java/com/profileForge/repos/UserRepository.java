package com.profileForge.repos;

import com.profileForge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUserUrl(String userUrl);
    Optional<User>  findByEmail(String email);

    Optional<User> findByEmailAndPassword(String  email,String password);

      boolean existsByUserUrl(String  userUrl);




}
