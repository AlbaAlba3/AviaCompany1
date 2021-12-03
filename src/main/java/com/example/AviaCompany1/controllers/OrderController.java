package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.model.Cart;
import com.example.AviaCompany1.model.Order;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.UserRepository;
import com.example.AviaCompany1.service.CartService;
import com.example.AviaCompany1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller

public class OrderController {
    private final String ERROR_ORDER_MESSAGE = "Заполните пустые поля";
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserContactsService userContactsService;
//
//    @Autowired
//    private UserContactsRepository userContactsRepository;

    @GetMapping("/ordering")
    public String getMakeOder(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User user=userRepository.findByUsername(username);
        Cart cart = user.getCart();
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(user);
//            user.setCart(new Cart());
//        }
//        UserContacts userContacts = userContactsService.getUserContactsByUser(user);
        model.addAttribute("cart", cart);
//        model.addAttribute("userContacts", userContacts);
        return "ordering";
    }

    @PostMapping("ordering")
    public String MakeOrder(@AuthenticationPrincipal User user, @RequestParam Cart cart, @RequestParam String address, @RequestParam String phone, Model model) {
        if (StringUtils.isEmpty(address) || StringUtils.isEmpty(phone)) {
            model.addAttribute("cart", cart);
//            UserContacts userContacts = userContactsService.getUserContactsByUser(user);
//            model.addAttribute("userContacts", userContacts);
            model.addAttribute("error", ERROR_ORDER_MESSAGE);
            return "ordering";
        }
        orderService.makeOrder(user, cart, address, phone);
        cartService.clearCart(cart.getId());

        return "redirect:/shop";
    }

    @GetMapping("{order}")
    public String getOrder(@AuthenticationPrincipal User user, @PathVariable Order order, Model model) {
        if (orderService.findAllByUser(user).contains(order)) {
            model.addAttribute("order", order);
//            model.addAttribute("userContacts", userContactsRepository.findById(order.getUser().getUserContacts().getId()).get());
            return "order";
        } else {
            return null;
        }
    }
}
