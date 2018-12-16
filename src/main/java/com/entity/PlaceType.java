package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class PlaceType {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name couldn't be Null")
    @Size(min= 5, message="Name should have at least 5 characters")
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
