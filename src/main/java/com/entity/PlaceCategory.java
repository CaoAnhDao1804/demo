package com.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class PlaceCategory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name couldn't be Null")
    @Size(min=5, max = 20, message="Name should have at least 5 characters and maximum 20 charaters")
    String name;

    Long idPlaceType;

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

    public Long getIdPlaceType() {
        return idPlaceType;
    }

    public void setIdPlaceType(Long idPlaceType) {
        this.idPlaceType = idPlaceType;
    }
}
