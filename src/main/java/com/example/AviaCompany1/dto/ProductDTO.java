package com.example.AviaCompany1.dto;

import lombok.Data;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Date;


public class ProductDTO {

    private Integer places;

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    private Long id;

    private String name;

    public String getNamein() {
        return namein;
    }

    public void setNamein(String namein) {
        this.namein = namein;
    }

    private String namein;

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
