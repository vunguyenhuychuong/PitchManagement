package com.example.pitchmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/home")
    public String HomePage(){

        return "index.html";
    }
}
