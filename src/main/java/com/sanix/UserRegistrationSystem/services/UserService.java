package com.sanix.UserRegistrationSystem.services;

import com.sanix.UserRegistrationSystem.dto.UserDTO;

import java.util.List;

public interface UserService {

    //UserDTO findByName(String name);

    UserDTO findById(Long l);
}
