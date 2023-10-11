package com.profileForge.repos;

import com.profileForge.models.SocialHandel;
import com.profileForge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialHandelRepo extends JpaRepository<SocialHandel,Integer> {

    Optional<SocialHandel> findByUser(User user);
}
