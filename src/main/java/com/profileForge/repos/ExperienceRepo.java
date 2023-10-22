package com.profileForge.repos;

import com.profileForge.models.Experience;
import com.profileForge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExperienceRepo  extends JpaRepository<Experience ,String > {

    List<Experience> findAllByUser(User user);

}
