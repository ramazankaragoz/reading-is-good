package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {


    Customer save(Customer customer);

    Page<Customer> findAll(Pageable pageable);
}
