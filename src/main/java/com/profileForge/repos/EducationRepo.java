package com.profileForge.repos;

import com.profileForge.models.Education;
import com.profileForge.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EducationRepo extends JpaRepository<Education, String >
{

    List<Education> findByUser(User user);

}
