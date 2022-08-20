package com.scb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBoot {


    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class,args);
    }

    @RequestMapping("/")
    public String name(){
        return "Hello , Welcome to Chennai";
    }
}
