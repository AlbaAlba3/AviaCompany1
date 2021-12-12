package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.model.CartItem;
import com.example.AviaCompany1.model.Product;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.CartRepository;
import com.example.AviaCompany1.repo.UserRepository;
import com.example.AviaCompany1.service.CartService;
import com.example.AviaCompany1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;



    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id,Model model)
    {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username=auth.getName();
//
//        CartItem cartItem=new CartItem();
//        User user = userRepository.findByUsername(username);
//        cartItem.setProduct(productService.getProductById(id).get());
//        CartService.cartitems.add(cartItem);

        CartService.cart.add(productService.getProductById(id).get());

//        if(productService.getProductById(id).get().getPrice()>user.getBalance())
//        {
//            return "unlucky";
//        }
        return "redirect:/shop";
    }

    @GetMapping("cart")
    public String cartGet(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        model.addAttribute("cartCount",CartService.cart.size());
        model.addAttribute("total",CartService.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",CartService.cart);
        model.addAttribute("user",userRepository.findByUsername(username));


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
        model.addAttribute("total",CartService.cart.stream().mapToDouble(Product::getPrice).sum());

        return "ordering";
    }

}
