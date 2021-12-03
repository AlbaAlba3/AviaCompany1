package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.model.Product;
import com.example.AviaCompany1.model.Role;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.CartRepository;
import com.example.AviaCompany1.service.CartService;
import com.example.AviaCompany1.service.ProductService;
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
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id)
    {
        CartService.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("cart")
    public String cartGet(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();

        model.addAttribute("carts", cartService.getCartByUser(username));
        model.addAttribute("cartCount",CartService.cart.size());
        model.addAttribute("total",CartService.cart.stream().mapToDouble(Product::getId).sum());
        model.addAttribute("cart",CartService.cart);
//        userRepository.save(user);

        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index)
    {
        CartService.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model)
    {
        model.addAttribute("total",CartService.cart.stream().mapToDouble(Product::getId).sum());
        return "ordering";
    }

}
