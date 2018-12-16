package com.controller;

import com.dto.APIResponseDTO;
import com.entity.ResourceTable;
import com.service.ResourceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class ResourceTableController {

    @Autowired
    ResourceTableService resourceTableService;

    @GetMapping(value = "/api/tables")
    public APIResponseDTO getAllResources(){
        return new APIResponseDTO(200, "OK",resourceTableService.getAllResources());
    }
}
