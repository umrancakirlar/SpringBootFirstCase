package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.ProductCommentDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto result=productService.createProduct(productDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products=productService.getProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDto> getProduct(Long id){
        ProductDto productDto=productService.getProduct(id);
        return ResponseEntity.ok(productDto);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        ProductDto result=productService.updateProduct(id, productDto);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id")Long id){
        Boolean status=productService.deleteProduct(id);
        return ResponseEntity.ok(status);

    }

    @GetMapping("/expiredProducts")
    public ResponseEntity<List<ProductDto>> getExpiredProducts(){
        List<ProductDto> products=productService.getExpiredProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/notExpiredProducts")
    public ResponseEntity<List<ProductDto>> getNotExpiredProducts(){
        List<ProductDto> products=productService.getNotExpiredProducts();
        return ResponseEntity.ok(products);
    }
}
