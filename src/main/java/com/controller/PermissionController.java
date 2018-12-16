package com.controller;

import com.dto.APIResponseDTO;
import com.entity.Permiss;
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
        return new APIResponseDTO(200,"Ok", permissService.getAllActionDTOOfTableByUser((long) idUser, (long) idTable));
    }

    @GetMapping(value = "/api/permissions/{idUser}")
    public APIResponseDTO getAllPermissionsOfUser(@PathVariable Long idUser){
        return new APIResponseDTO(200, "OK", permissService.getAllPermissionDTOOfUser(idUser));
    }

    @DeleteMapping(value = "/delete-action-table/{idUser}/{idTable}/{idAction}")
    public APIResponseDTO deleteActionTable(@PathVariable Long idUser, @PathVariable Long idTable, @PathVariable Long idAction){
        Permiss permissCurrent = new Permiss();
        permissCurrent.setIdAction(idAction);
        permissCurrent.setIdResource(idTable);
        permissCurrent.setIdUser(idUser);
        return new APIResponseDTO(200,"Delete!", permissService.deletePermissionOfUser(permissCurrent));
    }

    @DeleteMapping(value = "/delete-action-table-by-idPermiss/{idPermiss}")
    public APIResponseDTO deletePermissTableByIdPermiss(@PathVariable Long idPermiss){
        permissService.deletePermissById(idPermiss);
        return new APIResponseDTO(200,"Delete!",null );
    }


    @PostMapping(value = "/add-action-table")
    public APIResponseDTO addNewActionTableForUser(@RequestBody Permiss permiss){
        if(permiss.getIdUser()==0|| permiss.getIdAction() == 0|| permiss.getIdResource() == 0){
            return new APIResponseDTO(500, "Null value id", null);
        } else {
            if (permissService.checkExistedPermiss(permiss)){
                return new APIResponseDTO(500, "Permission existed!", null);
            } else {
                return new APIResponseDTO(200, "Created!", permissService.addPermissionForUser(permiss));
            }
        }
    }


}
