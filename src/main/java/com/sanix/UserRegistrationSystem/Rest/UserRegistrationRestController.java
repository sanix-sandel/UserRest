package com.sanix.UserRegistrationSystem.Rest;

import com.sanix.UserRegistrationSystem.Exception.CustomErrorType;
import com.sanix.UserRegistrationSystem.dto.UserDTO;
import com.sanix.UserRegistrationSystem.repository.UserJpaRepository;
import com.sanix.UserRegistrationSystem.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
    public static final Logger logger=
            LoggerFactory.getLogger(UserRegistrationRestController.class);

    private UserService userService;
    private final UserJpaRepository userJpaRepository;

    public UserRegistrationRestController(UserService userService, UserJpaRepository userJpaRepository) {
        this.userService = userService;
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers(){
        List<UserDTO> users=userJpaRepository.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    @PostMapping(value="/", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user){
        if(userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<UserDTO>(new CustomErrorType(
                    "Unable to create new user. A user with name "
                    +user.getUsername()+" already exist."
            ), HttpStatus.CONFLICT);
        }

        userJpaRepository.save(user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity <UserDTO> getUserById(@PathVariable("id") final Long id){

        UserDTO user=userService.findById(id);
        if(user==null){
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType("User with id "
                    +id+" not found"), HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity <UserDTO>(user, HttpStatus.OK);
    }

    @PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user){

        //fetch user based on id and set id to currentUSer object of type UserDTO
        UserDTO currentUser=userService.findById(id);

        if(currentUser==null){
            return new ResponseEntity<UserDTO>(new CustomErrorType("Unable to update. User with id " +
                    id+" not found."), HttpStatus.NOT_FOUND);
        }

        //update currentUser object data with user object data
        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());

        //save currentUser object
        userJpaRepository.saveAndFlush(currentUser);

        return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id){
        UserDTO user=userService.findById(id);
        if(user==null){
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType("Unable to delete. User with id "
                    +id+" not found ."), HttpStatus.NOT_FOUND);
        }
        userJpaRepository.delete(user);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }
}
