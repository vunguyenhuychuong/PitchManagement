package com.example.pitchmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/home", "/"})
    public String HomePage() {
        return "home/index.html";
    }
}
