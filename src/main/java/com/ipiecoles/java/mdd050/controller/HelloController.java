package com.ipiecoles.java.mdd050.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sayHello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }
}
