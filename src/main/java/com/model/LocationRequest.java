package com.model;


import com.validation.PhoneValid;
import lombok.Data;

import javax.validation.constraints.*;

@Data

public class LocationRequest {

    @NotNull(message = "Name couldn't be Null")
    @Size(min= 10, message="Name should have at least 10 characters")
    String name;

    @NotNull(message = "Introduction couldn't be Null")
    @Size(min=10, message="Introduction should have atleast 10 characters")
    String introduction;

    @NotNull(message = "Id Plcae Category couldn't be null")
    @Min(value = 1, message = "Id Place Category should greater than 1 or equal 1")
    Long idPlaceCategory;

    @NotNull(message = "Content couldn't be null")
    @NotBlank(message = "Content couldn't blank")
    String content;

    Long idStatus;

    @NotNull(message = "Address couldn't be null")
    @NotBlank(message = "Address couldn't blank")
    String address;


    @PhoneValid
    String phone;

    @Email
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
