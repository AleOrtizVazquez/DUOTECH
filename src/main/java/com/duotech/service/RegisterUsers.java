package com.duotech.service;


import com.duotech.entity.ApiPetition;

import java.util.Map;

public interface RegisterUsers {

    Map usersRegisters(ApiPetition request_in);

    String convertMD5(String pwd);
}

