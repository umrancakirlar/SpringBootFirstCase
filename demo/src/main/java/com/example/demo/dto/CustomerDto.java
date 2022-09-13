package com.example.demo.dto;

import lombok.Data;


@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
}
