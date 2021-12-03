package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.repo.UserRepository;
import com.example.AviaCompany1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller

public class MainController {

    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tittle", "Главная страница");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("user",userRepository.findByUsername(username));
        return "home";
    }


    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("user",userRepository.findByUsername(username));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String shop(Model model, @PathVariable long id) {
        model.addAttribute("product", productService.getProductById(id).get());

        return "viewProduct";
    }

}