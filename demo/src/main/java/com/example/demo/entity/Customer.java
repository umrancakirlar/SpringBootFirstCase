package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class Customer {
    @Id
    @SequenceGenerator(name="customer_seq_gen",sequenceName = "customer_gen",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_seq_gen")
    private long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String lastname;
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phone;

}
