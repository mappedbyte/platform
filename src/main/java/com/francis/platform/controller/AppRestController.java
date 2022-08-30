package com.francis.platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Francis
 */
@RestController
@RequestMapping("/app")
public class AppRestController {




    @GetMapping("/test")
    public String test(){
        return "测试";
    }

}
