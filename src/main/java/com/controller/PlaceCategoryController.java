package com.controller;

import com.dto.APIResponseDTO;
import com.entity.PlaceCategory;
import com.entity.PlaceType;
import com.service.PlaceCategoryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class PlaceCategoryController {

    @Autowired
    PlaceCategoryService placeCategoryService;

    @GetMapping(value = "/place-categories")
    @PreAuthorize("hasAuthority('VIEW_PLACECATEGORY') or hasAuthority('admin')")
//    @PreAuthorize("hasAuthority('user')")
    public APIResponseDTO findAll(){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findAllPlaceCategory());
    }

    @GetMapping(value = "/place-type/{id}/place-categories")
    @PreAuthorize("hasAuthority('VIEW_PLACECATEGORY') or hasAuthority('admin')")
    public  APIResponseDTO findAllPlaceCategoryOfOneType(@PathVariable Long id){
        return  new APIResponseDTO(200,"Success", placeCategoryService.findAllPlaceCategoryOfOneType(id));
    }

    @GetMapping(value = "/place-category/{id}")
    @PreAuthorize("hasAuthority('VIEW_PLACECATEGORY') or hasAuthority('admin')")
    public  APIResponseDTO getPlaceCategory( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findById(id));
    }

    @GetMapping(value = "app/place-category/{id}")
    public  APIResponseDTO getInfoPlaceCategory( @PathVariable Long id){
        return  new APIResponseDTO(200,"Success!",placeCategoryService.findCategoryInfoForApp(id));
    }

    @PostMapping(value = "/place-category")
    @PreAuthorize("hasAuthority('ADD_PLACECATEGORY') or hasAuthority('admin')")
    public APIResponseDTO  createPlaceCategory(@Valid  @RequestBody PlaceCategory placeCategory){
        placeCategoryService.createPlaceCategory(placeCategory);
        return  new APIResponseDTO(201,"Created!",placeCategory);
    }

    @PutMapping(value = "/place-category/{id}")
    @PreAuthorize("hasAuthority('EDIT_PLACECATEGORY') or hasAuthority('admin')")
    public APIResponseDTO editPlaceCategory(@Valid @RequestBody PlaceCategory placeCategory, @PathVariable Long id){
        PlaceCategory placeCategoryOld = placeCategoryService.findById(id).orElse(new PlaceCategory());
        System.out.print(placeCategory.getIdPlaceType());
        if (placeCategoryOld == null) return new APIResponseDTO(200, "Not Existed!", null);
        placeCategoryOld.setName(placeCategory.getName());
        placeCategoryService.updatePlaceCategory(placeCategoryOld);
        return new APIResponseDTO(200, "Edited", placeCategoryOld);

    }

    @DeleteMapping(value = "/place-category/{id}")
    @PreAuthorize("hasAuthority('DEL_PLACECATEGORY') or hasAuthority('admin')")
    public APIResponseDTO deletePlaceCategory(@PathVariable long id) {

        if (placeCategoryService.existLocationwithCategoryId(id)){
            return new APIResponseDTO (500, "Cannot delete!", null);
        } else {
            placeCategoryService.deletePlaceCategory(id);
            return  new APIResponseDTO(200,"Deleted successful!", null);
        }

    }




}
