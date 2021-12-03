package com.example.AviaCompany1.model;

import javax.persistence.*;

@Entity
@Table(name = "ordered_product")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;




    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderedProduct() {
    }

    public OrderedProduct(Order order, Product product, int amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}
