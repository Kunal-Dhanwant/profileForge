package com.profileForge.repos;

import com.profileForge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmailAndPassword(String  email,String password);

      boolean existsByUserName(String  userName);




}
