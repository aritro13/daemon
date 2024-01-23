package com.trading.daemon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trading.daemon.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("traderDaemon")
public class InstrumentHistoryController {


    @Autowired
    AccessTokenService accessTokenService;

    @GetMapping("loginUpstox")
    public String loginUpstox() throws JsonProcessingException {
        return "Success";
    }

    @GetMapping("authorized")
    public String upstoxAuthorized(@RequestParam String code, @RequestParam String state) throws JsonProcessingException {
        System.out.println(accessTokenService.getAccessToken(code, state));
        return "Login Success";
    }

}
