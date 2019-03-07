package com.cxy.security.cxysecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * Created by Caoxingyun on 2019/03/07
 */
@RestController
@RequestMapping(value = "security/demo")
public class SecurityCoreController {

    @GetMapping("/security")
    public String security(){
        return "hello security";
    }
}
