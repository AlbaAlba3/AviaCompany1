package com.example.AviaCompany1.service;

import com.example.AviaCompany1.model.Cart;
import com.example.AviaCompany1.model.Product;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;



    public List<Product> getAllProduct(){ return productRepository.findAll();}



    public void addProduct(Product product)
    {

        productRepository.save(product);
    }

    public void removeProductById(long id)
    {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(long id)
    {
        return productRepository.findById(id);
    }

    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }



}
