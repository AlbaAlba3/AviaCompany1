package com.example.AviaCompany1.controllers;


import com.example.AviaCompany1.dto.ProductDTO;
import com.example.AviaCompany1.repo.ProductRepository;
import com.example.AviaCompany1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(
        method={RequestMethod.POST, RequestMethod.GET}
)

public class AdminController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }


    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String productsAddGet(Model model){
        model.addAttribute("productsDTO",new ProductDTO());
        return "productsAdd";
    }

}
