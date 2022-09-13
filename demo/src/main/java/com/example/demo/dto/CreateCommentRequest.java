package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCommentRequest {
    private Long id;
    private String comment;
    private Date commentDate;
    private Long productId;
    private Long customerId;
}
