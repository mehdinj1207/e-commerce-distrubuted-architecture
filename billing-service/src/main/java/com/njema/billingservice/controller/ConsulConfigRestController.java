package com.njema.billingservice.controller;

import com.njema.billingservice.MyConsulConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RefreshScope
public class ConsulConfigRestController {
    @Autowired
    private MyConsulConfig myConsulConfig;
    //@Value("${token.accessTokenTimeout}")
    //private Long accessTokenTimeout;
    //@Value("${token.refreshTokenTimeout}")
   // private Long refreshTokenTimeout;

    @GetMapping("/myConfig")
    MyConsulConfig myConfig(){
        return myConsulConfig;
        //return Map.of("accessTokenTimeout",accessTokenTimeout,"refreshTokenTimeout",refreshTokenTimeout);
    }
}
