package com.AgileTracker.tracker.controller;

import com.AgileTracker.tracker.exceptions.GenericException;
import com.AgileTracker.tracker.model.Sprint;
import com.AgileTracker.tracker.repository.SprintRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/sprint")
@JsonIgnoreProperties(value={"managerId"}, allowGetters = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property = "id")
public class SprintController {

    @Autowired
    SprintRepository sprintRepository;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Sprint addSprint(@Valid @RequestBody Sprint sprint){
        return sprintRepository.save(sprint);
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Sprint editSprint(@Valid @RequestBody Sprint user){
        return sprintRepository.save(user);
    }

    @GetMapping("/addManager/{id}")
    public Sprint addManager(@PathVariable(value="id") Long id) throws GenericException {
        return sprintRepository.findById(id).orElseThrow(
                () -> new GenericException("Error getting project."));
    }


    @GetMapping("/{id}")
    public Sprint getSprintById(@PathVariable(value="id") Long id) throws GenericException {
        return sprintRepository.findById(id).orElseThrow(
                () -> new GenericException("Error getting project."));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable(value="id") Long id) throws GenericException{
        Sprint sprint = sprintRepository.findById(id).orElseThrow(
                () -> new GenericException("Error getting project."));
        sprintRepository.delete(sprint);
        return ResponseEntity.ok().build();
    }

}
