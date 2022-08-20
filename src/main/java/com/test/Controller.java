package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    public static final Logger log = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/hello/{name}")
    public String getName(@PathVariable String name){
        return "Hi "+name;
    }
}
