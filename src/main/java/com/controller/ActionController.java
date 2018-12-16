package com.controller;

import com.dto.APIResponseDTO;
import com.entity.ActionTable;
import com.entity.Permiss;
import com.service.ActionTableService;
import com.service.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)
public class ActionController {

    @Autowired
    PermissService permissService;

    @Autowired
    ActionTableService actionTableService;

    @GetMapping(value = "/api/actions")
    public APIResponseDTO getAllActions(){
        return new APIResponseDTO(200, "Ok", actionTableService.getAllActions());
    }

    @GetMapping(value = "/api/actions/{idUser}")
    public APIResponseDTO getAllActionsOfUser(@PathVariable Long idUser){
        List<Permiss> listActionOfUser = permissService.getAllPermissOfUser(idUser);
        return new APIResponseDTO(200, "OK", listActionOfUser);
    }

    @PostMapping(value = "/api/action")
    public APIResponseDTO addActionForUser(@RequestBody Permiss permiss){
        return new APIResponseDTO(200, "Created!", permissService.addPermissionForUser(permiss));
    }

    @DeleteMapping(value = "/api/action")
    public APIResponseDTO deleteActionOfUser(@RequestBody Permiss permiss){
        return new APIResponseDTO(200, "Deleted!", permissService.deletePermissionOfUser(permiss));
    }


}
