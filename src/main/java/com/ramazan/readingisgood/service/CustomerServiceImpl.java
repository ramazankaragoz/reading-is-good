package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.repository.AuthoritiesRepository;
import com.ramazan.readingisgood.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final AuthoritiesRepository authoritiesRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AuthoritiesRepository authoritiesRepository) {
        this.customerRepository = customerRepository;
        this.authoritiesRepository = authoritiesRepository;
    }


    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


}
