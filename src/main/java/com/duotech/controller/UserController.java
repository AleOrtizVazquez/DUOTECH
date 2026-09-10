package com.duotech.controller;

import com.duotech.entity.ApiPetition;
import com.duotech.service.RegisterUsers;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    RegisterUsers user_register;

    @CrossOrigin
    @RequestMapping(value = "/registred", method = RequestMethod.POST)
    public ResponseEntity<Object> registredAddress(HttpServletResponse res_pay,
                                                   @RequestBody ApiPetition login
    ) {
        res_pay.setHeader("Cache-Control", "no-cache,must-revalidate,max-age=0,no-store,private");
        Map loginPortalResponse = user_register.usersRegisters(login);
        return new ResponseEntity<>(loginPortalResponse, HttpStatus.valueOf(Integer.parseInt(loginPortalResponse.get("code").toString())));
    }
}
