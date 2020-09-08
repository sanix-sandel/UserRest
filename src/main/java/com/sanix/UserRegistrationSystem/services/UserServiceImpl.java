package com.sanix.UserRegistrationSystem.services;

import com.sanix.UserRegistrationSystem.dto.UserDTO;
import com.sanix.UserRegistrationSystem.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDTO findById(Long Id){

        Optional<UserDTO> user=userJpaRepository.findById(Id);

        return user.get();
    }


}
