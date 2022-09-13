package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.exception.NotFound;
import com.example.demo.service.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository userRepository, ModelMapper modelMapper) {
        this.customerRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer =modelMapper.map(customerDto, Customer.class);
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    public List<CustomerDto> getCustomers() {
        List<Customer> users=customerRepository.findAll();
        List<CustomerDto> dtos=users.stream().map(user -> modelMapper.map(user, CustomerDto.class)).collect(Collectors.toList());
        return  dtos;
    }

    public CustomerDto updateUser(Long id, CustomerDto userDto) {
        Optional<Customer> user=customerRepository.findById(id);
        if(user.isPresent()){
            user.get().setName(userDto.getName());
            user.get().setLastname(userDto.getLastname());
            user.get().setEmail(userDto.getEmail());
            user.get().setPhone(userDto.getPhone());
            return modelMapper.map(customerRepository.save(user.get()), CustomerDto.class);
        }
        throw new NotFound("User not found");
    }

    public Boolean deleteUser(Long id) {
        Optional<Customer> user=customerRepository.findById(id);
        if(user.isPresent()){
            customerRepository.deleteById(id);
            return true;
        }
        throw new NotFound("User not found");
    }

    /*public List<ProductCommentDto> getComments(Long id) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isPresent()){
           List<ProductComment> allcom=customer.get().getComments();
            List<ProductCommentDto> dtos=allcom.stream().map(productComment -> modelMapper.map(productComment, ProductCommentDto.class)).collect(Collectors.toList());
            return dtos;
        }
        throw new CustomerNotFound("User not found");
    }*/

    public CustomerDto getCustomer(Long id) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isPresent()){
            return modelMapper.map(customer.get(), CustomerDto.class);
        }
        throw new NotFound("User not found");
    }


}

