package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Will persist new customers.You can add a customer to the system. And you can login with this customer.")
    @PostMapping("/register")
    public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer){
        return new ResponseEntity<>(customerService.save(customer),HttpStatus.CREATED);
    }

    @Operation(summary = "Gets the customer list.")
    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(Pageable pageable){
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Will query all orders of the customer.Just enter page and size inside the body.")
    @GetMapping("/product/{id}")
    public ResponseEntity<Page<Order>> findAllProductByCustomerId(@PathVariable("id") UUID id,
                                                                  Pageable pageable){
        return new ResponseEntity<>(customerService.findAllProductByCustomerId(id,pageable), HttpStatus.OK);
    }
}
