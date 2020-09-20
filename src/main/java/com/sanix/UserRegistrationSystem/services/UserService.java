package com.sanix.UserRegistrationSystem.services;

import com.sanix.UserRegistrationSystem.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO findByUsername(String name);

    UserDTO findById(Long l);
}
