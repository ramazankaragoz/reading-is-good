package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(Pageable pageable){
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }
}
