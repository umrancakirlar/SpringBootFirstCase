package com.example.demo.service.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Long> {

    List<ProductComment> findByCommentDateBetween(Date start, Date end);

    List<ProductComment> findAllByCustomerId(Customer customer);

    List<ProductComment> findAllByProductId(Product product);
}
