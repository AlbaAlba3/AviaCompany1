package com.example.AviaCompany1.controllers;


import com.example.AviaCompany1.model.Role;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        boolean check=true;
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            check=false;
            model.put("check", check);
            return "registration";
        }

        user.setBalance(0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
