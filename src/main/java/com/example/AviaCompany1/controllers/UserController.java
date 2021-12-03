package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.model.Role;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userlist(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "userList";
    }

    @GetMapping("/edit/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles",Role.values());
        return "userEdit";
    }

    @GetMapping("/delete/{user}")
    public String userDeleteForm(@PathVariable User user) {
        userRepository.delete(user);
        return "redirect:/user";
    }


    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Integer balance,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user

    )
    {
        user.setUsername(username);
        user.setBalance(balance);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();


        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }



        userRepository.save(user);

        return "redirect:/user";
    }
}
