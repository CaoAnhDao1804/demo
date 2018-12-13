package com.controller;

import com.dto.APIResponseDTO;
import com.service.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class PermissionController {

    @Autowired
    PermissService permissService;

    @GetMapping(value = "/user-tables/{idUser}")
    public APIResponseDTO getAllTablesOfUser(@PathVariable int idUser){
        return new APIResponseDTO(200,"Ok", permissService.getAllTableOfUser((long) idUser));
    }

    @GetMapping(value = "/user-table-actions/{idUser}/{idTable}")
    public APIResponseDTO getAllActionOfTableByUser(@PathVariable int idUser, @PathVariable int idTable){
        return new APIResponseDTO(200,"Ok", permissService.getAllActionOfTableByUser((long) idUser, (long) idTable));
    }


}
