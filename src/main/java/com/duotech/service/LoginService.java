package com.duotech.service;

import com.duotech.entity.ApiPetition;

import java.util.Map;

public interface LoginService {


    Map loginPortal(ApiPetition petition_in);

    String convertMD5(String pwd);

}

