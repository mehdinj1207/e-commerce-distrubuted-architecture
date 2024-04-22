package com.njema.billingservice.controller;


import com.njema.billingservice.MyVaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class VaultConfigRestController {
    @Autowired
    private MyVaultConfig myVaultConfig;

    @GetMapping("/myVaultConfig")
    MyVaultConfig myConfig(){
        return myVaultConfig;
        }
}
