package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_gen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    private long id;
    private String productName;
    private double price;
    private Date discardDate;

}
