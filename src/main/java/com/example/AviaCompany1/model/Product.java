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

    private String namein;


    @DateTimeFormat(pattern = "HH:mm:ss yyyy-MM-dd")
    private String flyout;

    @DateTimeFormat(pattern = "HH:mm:ss yyyy-MM-dd")
    private String flyin;

    private Integer price;

    private Integer places;

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    private String description;

    public String getNamein() {
        return namein;
    }

    public void setNamein(String namein) {
        this.namein = namein;
    }

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
