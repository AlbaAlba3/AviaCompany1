package com.example.AviaCompany1.controllers;

import com.example.AviaCompany1.dto.ProductDTO;
import com.example.AviaCompany1.model.*;
import com.example.AviaCompany1.repo.CartRepository;
import com.example.AviaCompany1.repo.OrderedProductRepository;
import com.example.AviaCompany1.repo.UserRepository;
import com.example.AviaCompany1.service.CartService;
import com.example.AviaCompany1.service.OrderService;
import com.example.AviaCompany1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final String ERROR_ORDER_MESSAGE = "Заполните пустые поля";
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;



    @GetMapping("/ordering")
    public String getMakeOder(Model model) {

        model.addAttribute("cartCount",CartService.cart.size());
        model.addAttribute("total",CartService.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",CartService.cart);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("user",userRepository.findByUsername(username));

        return "ordering";
    }

    @GetMapping("/history")
    public String getHistory(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User user=userRepository.findByUsername(username);
        model.addAttribute("user",userRepository.findByUsername(username));

        Long id=user.getId();
        List<OrderedProduct> orderedProductsbyUser=new ArrayList<OrderedProduct>();
        List<OrderedProduct>  orderproducts=orderedProductRepository.findAll();

        for (OrderedProduct orderedProduct:orderproducts) {
            if(orderedProduct.getOrder().getUser().getId()==id)
            {
                orderedProductsbyUser.add(orderedProduct);
            }
        }

        model.addAttribute("history",orderedProductsbyUser);


        return "history";
    }

    @GetMapping("/history/remove/{id}")
    public String cartItemRemove(@PathVariable("id") Long id)
    {

        orderService.cancelorder(id);
        return "redirect:/history";
    }


    @PostMapping("/ordering")
    public String MakeOrder(Model model) {

        String message="ЗАКАЗ НЕ МОЖЕТ БЫТЬ ВЫПОЛНЕН,НЕДОСТАТОЧНО ДЕНЕГ НА БАЛАНСЕ";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User user=userRepository.findByUsername(username);

        Cart cart=cartService.getCartByUser(username);

        if (user.getBalance()>=cart.getTotalPrise()){
        orderService.makeOrder(username, cart);
        cartService.clearCart(CartService.cart);

        }
        else
        {
            model.addAttribute("message",message);
            model.addAttribute("user",user);
            return "unlucky";
        }

        return "redirect:/shop";
    }


}
