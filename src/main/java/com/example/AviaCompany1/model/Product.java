package com.example.AviaCompany1.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private double price;
    private double weight;
    private String description;

}
