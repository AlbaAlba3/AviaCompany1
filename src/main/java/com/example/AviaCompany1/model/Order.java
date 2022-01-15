package com.example.AviaCompany1.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = OrderedProduct.class, mappedBy = "order")
    private Set<OrderedProduct> orderedProducts = new HashSet<>();

    private int productsPrise;

//    @Column(name = "date_created", nullable = false)
//    @Temporal(TIMESTAMP)
//    private Date dateCreated;


//    @Enumerated(EnumType.STRING)
//    private Status status;

    public Order() {
    }

    public Order(User user, Set<OrderedProduct> orderedProducts, int productsPrise, String address, String phone, Status status) {
        this.user = user;
        this.orderedProducts = orderedProducts;
        this.productsPrise = productsPrise;
//        this.status =status ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public int getProductsPrise() {
        return productsPrise;
    }

    public void setProductsPrise(int productsPrise) {
        this.productsPrise = productsPrise;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
}