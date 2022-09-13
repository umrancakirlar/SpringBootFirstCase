package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.exception.NotFound;
import com.example.demo.service.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product=modelMapper.map(productDto,Product.class);
        return modelMapper.map(productRepository.save(product),ProductDto.class);
    }

    public List<ProductDto> getProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductDto> dtos=products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            product.get().setProductName(productDto.getProductName());
            product.get().setPrice(productDto.getPrice());
            product.get().setDiscardDate(productDto.getDiscardDate());
            return modelMapper.map(productRepository.save(product.get()),ProductDto.class);
        }
        throw new NotFound("Product not found");
    }

    public Boolean deleteProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        throw new NotFound("Product not found");
    }

    public List<ProductDto> getExpiredProducts() {
        List<Product> products=productRepository.findAll();
        List<Product> discardDates=new ArrayList<Product>();
        for(Product p: products){
            if(p.getDiscardDate()==null){
                continue;
            }
            if(p.getDiscardDate().compareTo(new Date())<0){
                discardDates.add(p);
            }
        }
        List<ProductDto> dtos=discardDates.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public List<ProductDto> getNotExpiredProducts() {
        List<Product> products=productRepository.findAll();
        List<Product> healthy=new ArrayList<Product>();
        for(Product p: products){
            if(p.getDiscardDate()==null){
                healthy.add(p);
            }
            else if(p.getDiscardDate().compareTo(new Date())>=0){
                healthy.add(p);
            }
        }
        List<ProductDto> dtos=healthy.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public ProductDto getProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            return modelMapper.map(product.get(), ProductDto.class);
        }
        throw new NotFound("User not found");
    }
}
