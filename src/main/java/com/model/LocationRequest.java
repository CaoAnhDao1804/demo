package com.model;


import lombok.Data;

import javax.validation.constraints.*;

@Data

public class LocationRequest {

    @NotNull(message = "Name couldn't be Null")
    @Size(min=2, message="Name should have at least 2 characters")
    String name;

    @NotNull(message = "Introduction couldn't be Null")
    @Size(min=2, message="Introduction should have atleast 2 characters")
    String introduction;

    @NotNull(message = "Id Plcae Category couldn't be null")
    @Min(value = 1, message = "Id Place Category should greater than 1 or equal 1")
    Long idPlaceType;
    Long idPlaceCategory;

    @NotNull(message = "Content couldn't be null")
    @NotBlank(message = "Content couldn't blank")
    String content;

    Long idStatus;


    String address;


    Double longitudeAddress;
    Double latitudeAddress;

    String nameAddress;

    @NotNull(message = "Content couldn't be null")
    @NotBlank(message = "Content couldn't blank")
    String phone;

    @NotNull(message = "Email couldn't be null")
    @Email(message = "Email validation")
    String email;

    Long idDuration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getIdPlaceType() {
        return idPlaceType;
    }

    public void setIdPlaceType(Long idPlaceType) {
        this.idPlaceType = idPlaceType;
    }

    public Long getIdPlaceCategory() {
        return idPlaceCategory;
    }

    public void setIdPlaceCategory(Long idPlaceCategory) {
        this.idPlaceCategory = idPlaceCategory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Double getLongitudeAddress() {
        return longitudeAddress;
    }

    public void setLongitudeAddress(Double longitudeAddress) {
        this.longitudeAddress = longitudeAddress;
    }

    public Double getLatitudeAddress() {
        return latitudeAddress;
    }

    public void setLatitudeAddress(Double latitudeAddress) {
        this.latitudeAddress = latitudeAddress;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdDuration() {
        return idDuration;
    }

    public void setIdDuration(Long idDuration) {
        this.idDuration = idDuration;
    }
}
