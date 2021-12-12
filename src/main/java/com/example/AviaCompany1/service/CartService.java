package com.example.AviaCompany1.service;

import com.example.AviaCompany1.model.Cart;
import com.example.AviaCompany1.model.CartItem;
import com.example.AviaCompany1.model.Product;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.CartItemRepository;
import com.example.AviaCompany1.repo.CartRepository;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemRepository cartItemRepository;

    User user;


    CartItem cartItem=new CartItem();

    public static List<Product> cart;

    static {
        cart = new ArrayList<Product>();
    }
    public static List<CartItem> cartitems;

    static {
        cartitems = new ArrayList<CartItem>();
    }


    public Cart getCart(Long id) {
        return cartRepository.findById(id).get();
    }

    public Cart getCartByUser(String username) {
        User user = userRepository.findByUsername(username);


        Cart cartuser =new Cart();


            for (Product product : cart) {
                cartItem.setProduct(product);
                cartItem.setAmount(1);
                cartItem.setCart(cartuser);
                cartitems.add(cartItem);
//                cartItemRepository.save(cartItem);
            }



            cartuser.setUser(user);
            cartuser.setCartItems(cartitems);
            cartuser.setTotalItems(cartuser.getTotalItems());
            user.setCart(cartuser);

//        if (user.getCart().getCartItems() == null) {
//            user.getCart().setCartItems(cartItems);
//        }
//
//        if(cartuser!=null)
//        { cartRepository.save(cartuser);}
//
//        Optional<Cart> cartOptional = cartRepository.findById(user.getCart().getId());
//
//        if (cartOptional.isPresent()) {
//            cartuser = cartOptional.get();
//        }


            return cartuser;
        }

    public void clearCart(List<Product> cart) {
        cartitems.clear();
        cart.clear();
    }

}

