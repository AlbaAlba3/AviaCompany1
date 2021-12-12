package com.example.AviaCompany1.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();


    private Integer totalItems;

    private Integer totalPrise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getTotalItems() {
        totalItems = 0;
        for (CartItem cartItem : cartItems) {
            totalItems += cartItem.getAmount();
        }
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPrise() {
        totalPrise = 0;
        for (CartItem cartItem : cartItems) {
            totalPrise += cartItem.getProduct().getPrice() * cartItem.getAmount();
        }

        return totalPrise;
    }

    public void setTotalPrise(Integer totalPrise) {
        this.totalPrise = totalPrise;
    }
}
