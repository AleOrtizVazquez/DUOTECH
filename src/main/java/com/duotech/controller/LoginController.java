package com.duotech.controller;

import com.duotech.entity.ApiPetition;
import com.duotech.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    LoginService loginp;

    @CrossOrigin
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public ResponseEntity<Object> AccessLogin(HttpServletResponse res_pay,
                                              @RequestBody ApiPetition login
    ) {
        res_pay.setHeader("Cache-Control", "no-cache,must-revalidate,max-age=0,no-store,private");
        Map loginPortalResponse = loginp.loginPortal(login);
        return new ResponseEntity<>(loginPortalResponse, HttpStatus.valueOf(Integer.parseInt(loginPortalResponse.get("code").toString())));
    }



    @PostMapping("/register")
    public MessageResponse register(@RequestBody RegisterRequest request) {
        return new MessageResponse(true, "Usuario registrado correctamente");
    }




    @PostMapping("/recover")
    public MessageResponse recover(@RequestBody RecoverRequest request) {
        return new MessageResponse(true, "Si el correo existe, se enviaron instrucciones de recuperación");
    }

    public record LoginRequest(String email, String password) {}
    public record RegisterRequest(String name, String email, String password) {}
    public record RecoverRequest(String email) {}
    public record LoginResponse(boolean success, String message, String email, String role) {}
    public record MessageResponse(boolean success, String message) {}
}
