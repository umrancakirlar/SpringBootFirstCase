package com.example.demo.dto;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import lombok.Data;

import java.util.Date;
@Data
public class ProductCommentDto {
    private Long id;
    private String comment;
    private Date commentDate;
    private Product productId;
    private Customer customerId;
}
