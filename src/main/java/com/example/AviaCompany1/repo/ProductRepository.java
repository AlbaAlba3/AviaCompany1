package com.example.AviaCompany1.repo;

import com.example.AviaCompany1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
