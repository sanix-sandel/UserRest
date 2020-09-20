package com.sanix.UserRegistrationSystem.repository;

import com.sanix.UserRegistrationSystem.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {

    Optional<UserDTO> findByUsername(String username);
}
