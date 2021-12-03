package com.example.AviaCompany1.service;

import com.example.AviaCompany1.model.Cart;
import com.example.AviaCompany1.model.CartItem;
import com.example.AviaCompany1.model.Product;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.CartRepository;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public static List<Product> cart;



    static {
        cart=new ArrayList<Product>();
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id).get();
    }

    public Cart getCartByUser(String username) {
        User user=userRepository.findByUsername(username);
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setCartItems(new LinkedList<CartItem>());
            user.setCart(cart);
            return cart;
        }

        if (user.getCart().getCartItems() == null) {
            user.getCart().setCartItems(new LinkedList<CartItem>());
        }


        Optional<Cart> cartOptional = cartRepository.findById(user.getCart().getId());

        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        }

        return cart;
    }

    public void clearCart(Long id) {
        Cart cart = cartRepository.findById(id).get();
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
