package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer){
        return new ResponseEntity<>(customerService.save(customer),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(Pageable pageable){
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Page<Order>> findAllProductByCustomerId(@PathVariable("id") UUID id,
                                                                  Pageable pageable){
        return new ResponseEntity<>(customerService.findAllProductByCustomerId(id,pageable), HttpStatus.OK);
    }
}
