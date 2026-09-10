package com.duotech.service.impl;

import com.duotech.entity.ApiPetition;
import com.duotech.entity.User;
import com.duotech.repository.UserRepository;
import com.duotech.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository repo_user;

    @Override
    public Map loginPortal(ApiPetition petition_in) {
        Map response = new HashMap();
        try {
            Map params = petition_in.getPetition();
            log.info("params "+params);

            boolean validate = true;
            String email=null;
            String pass=null;


            String username = (String) params.get("username");
            String password = (String) params.get("password");

            log.info("username "+username);
            log.info("password "+password);

            if (username != null && password != null) {
                 email = username.toString();
                 pass = password.toString();
            }else{
                validate=false;
            }
            if (validate) {
                log.info("email "+email);
                User find = repo_user.findTop1ByemailAndActive(email, true);
                log.info("find "+find);

                String passwordBD = find.getPassword();

                String passwordRequest = params.get("password").toString();

                String passwordMD5 = convertMD5(passwordRequest);

                if (passwordBD.equals(passwordMD5)) {

                    response.put("code", HttpStatus.SC_OK);
                    response.put("message", "The user is accept.");
                    response.put("status", true);


                } else {
                    response.clear();
                    response.put("code", HttpStatus.SC_BAD_REQUEST);
                    response.put("message", "The user is not registered.");
                    response.put("status", false);
                }
            }else {
                response.put("code", HttpStatus.SC_BAD_REQUEST);
                response.clear();
                response.put("message", "The user is not registered.");
                response.put("status", false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.clear(); //
            response.put("code", HttpStatus.SC_BAD_REQUEST);
            response.put("message", "error,review please this petition ");
            response.put("status", false);
        }
        return response;
    }




    @Override
    public String convertMD5(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(pwd.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
