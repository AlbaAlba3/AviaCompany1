package com.example.AviaCompany1.repo;

import com.example.AviaCompany1.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}