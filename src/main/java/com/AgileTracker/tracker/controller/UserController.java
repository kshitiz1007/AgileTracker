package com.AgileTracker.tracker.controller;

import com.AgileTracker.tracker.exceptions.GenericException;
import com.AgileTracker.tracker.model.User;
import com.AgileTracker.tracker.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property = "id")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User editUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/promoteToManager/{id}")
    public ResponseEntity<?> promoteToManager(@PathVariable(value="id") Long id) throws GenericException{
        User user = userRepository.findById(id).orElseThrow(
                () -> new GenericException("Error getting user."));
        user.setManager(true);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/demoteToManager/{id}")
    public ResponseEntity<?> demoteToManager(@PathVariable(value="id") Long id) throws GenericException{
        User user = userRepository.findById(id).orElseThrow(
                () -> new GenericException("Error getting user."));
        user.setManager(false);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value="id") Long id) throws GenericException{
        User user = userRepository.findById(id).orElseThrow(
                () -> new GenericException("Error getting user."));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
