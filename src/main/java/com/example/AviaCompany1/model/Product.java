package com.example.AviaCompany1.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @DateTimeFormat(pattern = "dd/MMM/yyyy HH:mm")
    private String flyout;

    @DateTimeFormat(pattern = "dd/MMM/yyyy HH:mm")
    private String flyin;

    private Integer price;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlyout() {
        return flyout;
    }

    public void setFlyout(String flyout) {
        this.flyout = flyout;
    }

    public String getFlyin() {
        return flyin;
    }

    public void setFlyin(String flyin) {
        this.flyin = flyin;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
