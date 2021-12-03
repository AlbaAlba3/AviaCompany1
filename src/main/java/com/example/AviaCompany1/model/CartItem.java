package com.example.AviaCompany1.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cartitems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    private int amount;


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
