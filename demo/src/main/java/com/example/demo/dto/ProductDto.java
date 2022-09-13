package com.example.demo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ProductDto {
    private Long id;
    private String productName;
    private double price;
    private Date discardDate;
}
