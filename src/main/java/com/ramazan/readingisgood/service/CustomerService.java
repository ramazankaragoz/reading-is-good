package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {


    Customer save(Customer customer);

    Page<Customer> findAll(Pageable pageable);

    Page<Order> findAllProductByCustomerId(UUID fkCustomerId, Pageable pageable);
}
