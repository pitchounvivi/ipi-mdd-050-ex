package com.ipiecoles.java.mdd050.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sayHello")
public class HelloController {

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "text/plain")
    public String hello(){
        return "hello world";
    }

    @RequestMapping(
            value = "/html",
            method = RequestMethod.GET,
            produces = "text/html")
    public String helloHtml(){
        return "<p>coucou</p>";
    }

}
