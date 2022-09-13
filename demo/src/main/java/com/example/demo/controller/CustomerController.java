package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.ProductCommentDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductComment;
import com.example.demo.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createUser(@RequestBody CustomerDto userDto){
        CustomerDto result=customerService.createCustomer(userDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getCustomers(){
        List<CustomerDto> users=customerService.getCustomers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(Long id){
        CustomerDto customerDto=customerService.getCustomer(id);
        return ResponseEntity.ok(customerDto);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomerDto> updateUser(@PathVariable("id") Long id, @RequestBody CustomerDto userDto){
        CustomerDto result =customerService.updateUser(id,userDto);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        Boolean status=customerService.deleteUser(id);
        return ResponseEntity.ok(status);
    }


}
