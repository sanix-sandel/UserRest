package com.sanix.UserRegistrationSystem.repository;

import com.sanix.UserRegistrationSystem.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {


}
