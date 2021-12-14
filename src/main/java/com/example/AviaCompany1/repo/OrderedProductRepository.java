package com.example.AviaCompany1.repo;

import com.example.AviaCompany1.model.Order;
import com.example.AviaCompany1.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {

}
