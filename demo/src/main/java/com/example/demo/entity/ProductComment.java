package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ProductComment {
    @Id
    @SequenceGenerator(name="productComment_seq_gen",sequenceName = "productComment_gen",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "productComment_seq_gen")
    private long id;
    @Column(length = 500)
    private String comment;
    private Date commentDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customerId;

}
