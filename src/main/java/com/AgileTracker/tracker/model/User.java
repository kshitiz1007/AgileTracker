package com.AgileTracker.tracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

    @Getter
    @Setter
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    private boolean manager;

}
