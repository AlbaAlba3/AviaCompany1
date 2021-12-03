package com.example.AviaCompany1.service;


import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository dao;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.example.AviaCompany1.model.User myuser= dao.findByUsername(userName);
        if (myuser == null) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = User.builder()
                .username(myuser.getUsername())
                .password(myuser.getPassword())
                .roles(myuser.getRoles().toString())
                .build();

        return user;
    }
}


