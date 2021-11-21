package com.example.AviaCompany1.service;

import com.example.AviaCompany1.model.Product;
import com.example.AviaCompany1.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(){ return productRepository.findAll();}
}
