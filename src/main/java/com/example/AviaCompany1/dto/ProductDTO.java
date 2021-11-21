package com.example.AviaCompany1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    public Long id;
    public String name;

    public Date price;
    public Date weight;
    public String description;

}
