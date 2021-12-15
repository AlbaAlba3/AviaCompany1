package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.model.Role;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/personal")
public class PersonalController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String user(Model model,@AuthenticationPrincipal User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("user",userRepository.findByUsername(username));
        return "personal";
    }

    @GetMapping("/edit/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "personalEdit";
    }



    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Integer balance,
            @RequestParam Integer age,
            @RequestParam String city,
            @RequestParam String name,
            @RequestParam("userId") User user

    )
    {
        user.setUsername(username);
        user.setBalance(balance);
        user.setAge(age);
        user.setCity(city);
        user.setName(name);


        userRepository.save(user);

        return "redirect:/personal";
    }
}
