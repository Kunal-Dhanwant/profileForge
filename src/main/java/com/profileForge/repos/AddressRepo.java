package com.profileForge.repos;

import com.profileForge.models.Address;
import com.profileForge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {


    Optional<Address> findByUser(User user);
}
