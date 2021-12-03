package com.example.AviaCompany1.repo;

import com.example.AviaCompany1.model.Order;
import com.example.AviaCompany1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}

