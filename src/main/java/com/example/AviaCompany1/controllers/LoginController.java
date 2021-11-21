package com.example.AviaCompany1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {



    @GetMapping("/login")
    public String blogMain(Model model){
        return "login";
    }
}
