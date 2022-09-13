package com.example.demo.controller;

import com.example.demo.dto.CreateCommentRequest;
import com.example.demo.dto.ProductCommentDto;
import com.example.demo.service.ProductCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class ProductCommentController {

    private final ProductCommentService productCommentService;

    public ProductCommentController(ProductCommentService productCommentService) {
        this.productCommentService = productCommentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCommentDto> createComment(@RequestBody CreateCommentRequest productCommentDto) {
        ProductCommentDto result = productCommentService.createComment(productCommentDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable("id") Long id) {
        Boolean status = productCommentService.deleteComment(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/customerComments/{customerId}")
    public ResponseEntity<List<ProductCommentDto>> getCustomerComments(@PathVariable("customerId") Long id) {
        List<ProductCommentDto> comments = productCommentService.getCustomerComments(id);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/productComments/{productId}")
    public ResponseEntity<List<ProductCommentDto>> getProductComments(@PathVariable("productId") Long id) {
        List<ProductCommentDto> comments = productCommentService.getProductComments(id);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/comments/{productId}")
    public ResponseEntity<List<ProductCommentDto>> getProductCommentsByDate (@PathVariable("productId") Long id, @RequestParam Date start,@RequestParam Date end) {
        List<ProductCommentDto> comments = productCommentService.getProductCommentsByDate(id,start,end);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/comments2/{customerId}")
    public ResponseEntity<List<ProductCommentDto>> getCustomerCommentsByDate (@PathVariable("customerId") Long id, @RequestParam Date start,@RequestParam Date end) {
        List<ProductCommentDto> comments = productCommentService.getCustomerCommentsByDate(id,start,end);
        return ResponseEntity.ok(comments);
    }

}
