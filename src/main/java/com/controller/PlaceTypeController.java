package com.controller;

import com.dto.APIResponseDTO;
import com.entity.PlaceType;
import com.service.PlaceTypeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
public class PlaceTypeController {

    @Autowired
    PlaceTypeService placeTypeService;

    @GetMapping( value = "/place-types")
//    @PreAuthorize("hasAuthority('admin') or  hasAuthority('mod')")
    @PreAuthorize("hasAuthority('VIEW_PLACETYPE') or hasAuthority('admin')")
    public APIResponseDTO getPlaceTypes(){
        return new APIResponseDTO(200,"Success!",placeTypeService.findAll());
    }

    @GetMapping(value = "/place-type/{id}")
    @PreAuthorize("hasAuthority('VIEW_PLACETYPE') or hasAuthority('admin')")
    public  APIResponseDTO getPlaceType( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeTypeService.findById(id));
    }

    @GetMapping(value = "/app/place-type/{id}")
    public  APIResponseDTO getPlaceTypeForApp( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeTypeService.findTypeResponseDTOByIdType(id));
    }

    @PostMapping(value = "/place-type")
    @PreAuthorize("hasAuthority('ADD_PLACETYPE') or hasAuthority('admin')")
    public APIResponseDTO createPlaceType(@Valid @RequestBody PlaceType placeType){
        placeTypeService.save(placeType);
        return  new APIResponseDTO(201,"Created!",placeType);

    }

    @PutMapping(value = "/place-type/{id}")
    @PreAuthorize("hasAuthority('EDIT_PLACETYPE') or hasAuthority('admin')")
    public APIResponseDTO  editPlaceType(@Valid @RequestBody PlaceType placeType, @PathVariable Long id){
        Optional<PlaceType> placeTypeOld = placeTypeService.findById(id);
        if (!placeTypeOld.isPresent()) return new APIResponseDTO(202, "not Exist", placeType);
        placeType.setId(id);
        placeTypeService.save(placeType);
        return new APIResponseDTO(200, "Edited", placeType);
    }

    @DeleteMapping(value = "/place-type/{id}")
    @PreAuthorize("hasAuthority('DEL_PLACETYPE')")
    public APIResponseDTO deleteStudent(@PathVariable long id) {
        if(placeTypeService.existCategorywithTypeId(id)){
            return new APIResponseDTO(500, "Cannot delete!", null);
        } else {
            placeTypeService.deleteById(id);
            return  new APIResponseDTO(200,"Deleted!", null);
        }
    }

}
