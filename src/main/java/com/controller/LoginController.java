package com.controller;

import com.dto.APIResponseDTO;
import com.dto.APIResponseDTOExtend;
import com.entity.Traveler;
import com.entity.Users;
import com.model.ActionUser;
import com.service.PermissService;
import com.service.TravelerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 1800)
public class LoginController {
    @Autowired
    TravelerService travelerService;

    @Autowired
    PermissService permissService;

    @PostMapping("/login")
    public APIResponseDTO login(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
        return new APIResponseDTO(200, "Login success!", travelerService.login(username, password));
    }

    @PostMapping("/login-web")
    public APIResponseDTOExtend loginWeb(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
        List<ActionUser> listActionUsers = new ArrayList<>();
        if (travelerService.findByEmail(username)!=null){
            listActionUsers = permissService.getAllActionUser(travelerService.findByEmail(username).getId());
        }
        if (listActionUsers.size() == 0){
            listActionUsers.add(new ActionUser("VIEW_PLACECATEGORY"));
            listActionUsers.add(new ActionUser("VIEW_PLACETYPE"));
            listActionUsers.add(new ActionUser("VIEW_LOCATION"));
            listActionUsers.add(new ActionUser("VIEW_USER"));
        }
        return new APIResponseDTOExtend(200, "Login success123!", travelerService.login(username, password), listActionUsers);
    }
}