package com.example.AviaCompany1.repo;

import com.example.AviaCompany1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
