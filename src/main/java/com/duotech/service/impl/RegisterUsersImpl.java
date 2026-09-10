package com.duotech.service.impl;


import com.duotech.entity.Account;
import com.duotech.entity.ApiPetition;
import com.duotech.entity.User;
import com.duotech.repository.AccountRepository;
import com.duotech.repository.UserRepository;
import com.duotech.service.RegisterUsers;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class RegisterUsersImpl implements RegisterUsers {


    @Autowired
    UserRepository repo_user;


    @Autowired
    AccountRepository repo_account;


    @Override
    @Transactional
    public Map usersRegisters(ApiPetition request_in) {
        Map response_add_users = new HashMap();
        try {

            Map petition_in = request_in.getPetition();
            log.info("petition_in" + petition_in);
            Account acc = repo_account.findTop1Byaccount(petition_in.get("account").toString());
            log.info("acc" + acc);
            Integer id = 0;
            String temp_account = "";
            if (!Objects.isNull(acc)) {

                User us = repo_user.findTop1ByemailAndAccountAndActive(petition_in.get("email").toString(), acc, true);
                log.info("us" + us);

                log.info("id \t: " + id + " -- \t :" + temp_account);

                if (Objects.isNull(us)) {
                    log.info("entro aqui al");
                    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    ZonedDateTime nowInMexico = ZonedDateTime.now(ZoneId.of("America/Mexico_City"));
                    String dateFull = formateador.format(nowInMexico);

                    User date_new = new User();
                    date_new.setAccount(acc);
                    date_new.setActive(false);
                    date_new.setName(petition_in.get("fullName").toString());
                    date_new.setMemberships(petition_in.get("memberships").toString());
                    date_new.setStartdate(petition_in.get("startDate").toString());
                    date_new.setEnddate(petition_in.get("endDate").toString());
                    date_new.setBirthdate(petition_in.get("birthdate").toString());
                    date_new.setEmail(petition_in.get("email").toString());
                    date_new.setAdditionalemail(petition_in.get("additionalEmail").toString());
                    date_new.setPhone(petition_in.get("phone").toString());
                    date_new.setAdditionalphone(String.valueOf(petition_in.get("additionalPhone")));
                    date_new.setAcceptpolicy(Boolean.parseBoolean(petition_in.get("acceptanceTermsConditions").toString()));
                    date_new.setPolicy(Boolean.parseBoolean(petition_in.get("privancyNoticeAcceptance").toString()));
                    date_new.setPassword(convertMD5(petition_in.get("password").toString()));
                    date_new.setActive(true);
                    date_new.setCreationDate(dateFull);
                    if (id != 0) {
                        date_new.setId(id);
                        date_new.setAccount(acc);
                    }

                    log.info("new new" + date_new);
                    User register = repo_user.save(date_new);
                    log.info("register" + register);
                    response_add_users.put("code", HttpStatus.SC_OK);
                    response_add_users.put("message", "registered user");
                    response_add_users.put("user ",register);
                    response_add_users.put("success", true);

                } else {
                    response_add_users.put("code", HttpStatus.SC_EXPECTATION_FAILED);
                    response_add_users.put("message", "A problem occurred while inserting the record.");
                    response_add_users.put("success", false);
                }

            } else {
                response_add_users.put("code", HttpStatus.SC_CONFLICT);
                response_add_users.put("message", "The user already exists");
                response_add_users.put("success", false);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            response_add_users.put("code", HttpStatus.SC_BAD_REQUEST);
            response_add_users.put("message", "The request could not be understood by the server due to malformed syntax FOR User register");
            response_add_users.put("success", false);
        }
        return response_add_users;
    }


    @SneakyThrows
    @Override
    public String convertMD5(String pwd) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        log.info("digest " + digest);

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02X", b));
        }
        log.info("pwd" + hexString);
        return hexString.toString().toLowerCase();
    }

}

