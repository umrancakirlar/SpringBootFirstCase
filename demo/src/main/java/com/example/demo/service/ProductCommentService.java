package com.example.demo.service;

import com.example.demo.dto.CreateCommentRequest;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.ProductCommentDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductComment;
import com.example.demo.exception.NotFound;
import com.example.demo.service.repository.ProductCommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCommentService {
    private final ProductCommentRepository productCommentRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductCommentService(ProductCommentRepository productCommentRepository, CustomerService customerService, ProductService productService, ModelMapper modelMapper) {
        this.productCommentRepository = productCommentRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.modelMapper = modelMapper;

    }

    public ProductCommentDto createComment(CreateCommentRequest createCommentRequest) {
        ProductComment comment=new ProductComment();
        CustomerDto customerDto=customerService.getCustomer(createCommentRequest.getCustomerId());
        ProductDto productDto=productService.getProduct(createCommentRequest.getProductId());
        Customer customer=modelMapper.map(customerDto, Customer.class);
        Product product=modelMapper.map(productDto, Product.class);
        comment.setComment(createCommentRequest.getComment());
        comment.setCommentDate(new Date());
        comment.setCustomerId(customer);
        comment.setProductId(product);
        return modelMapper.map(productCommentRepository.save(comment), ProductCommentDto.class);
    }


    public Boolean deleteComment(Long id) {
        Optional<ProductComment> productComment=productCommentRepository.findById(id);
        if(productComment.isPresent()){
            productCommentRepository.deleteById(id);
            return true;
        }
        throw new NotFound("User not found");
    }

    public List<ProductCommentDto> getCustomerComments(Long id) {
        CustomerDto customerDto=customerService.getCustomer(id);
        Customer customer=modelMapper.map(customerDto,Customer.class);
        List<ProductComment> comments=productCommentRepository.findAllByCustomerId(customer);
        List<ProductCommentDto> dtos=comments.stream().map(productComment -> modelMapper.map(productComment, ProductCommentDto.class)).collect(Collectors.toList());
        return dtos;

    }

    public List<ProductCommentDto> getProductComments(Long id) {
        ProductDto productDto=productService.getProduct(id);
        Product product=modelMapper.map(productDto,Product.class);
        List<ProductComment> comments=productCommentRepository.findAllByProductId(product);
        List<ProductCommentDto> dtos=comments.stream().map(productComment -> modelMapper.map(productComment, ProductCommentDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public List<ProductCommentDto> getCustomerCommentsByDate(Long id, Date start, Date end) {
        CustomerDto customerDto=customerService.getCustomer(id);
        Customer customer=modelMapper.map(customerDto,Customer.class);
        List<ProductComment> comments=productCommentRepository.findAllByCustomerId(customer);
        List<ProductComment> comments2=productCommentRepository.findByCommentDateBetween(start,end);
        comments.retainAll(comments2);
        List<ProductCommentDto> dtos=comments.stream().map(productComment -> modelMapper.map(productComment, ProductCommentDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public List<ProductCommentDto> getProductCommentsByDate(Long id, Date start, Date end) {
        ProductDto productDto=productService.getProduct(id);
        Product product=modelMapper.map(productDto,Product.class);
        List<ProductComment> comments=productCommentRepository.findAllByProductId(product);
        List<ProductComment> comments2=productCommentRepository.findByCommentDateBetween(start,end);
        comments.retainAll(comments2);
        List<ProductCommentDto> dtos=comments.stream().map(productComment -> modelMapper.map(productComment, ProductCommentDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
