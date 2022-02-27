package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Authorities;
import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.exception.UserAlreadyExistException;
import com.ramazan.readingisgood.repository.AuthoritiesRepository;
import com.ramazan.readingisgood.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthoritiesRepository authoritiesRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, AuthoritiesRepository authoritiesRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authoritiesRepository = authoritiesRepository;
    }


    @Override
    public Customer save(Customer customer) {

        if (customerRepository.existsByEmail(customer.getEmail())){
            throw new UserAlreadyExistException("A User is already registered with this email address.");
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        var savedCustomer=customerRepository.save(customer);

        authoritiesRepository.save(new Authorities(savedCustomer,"ROLE_USER"));

        return savedCustomer;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


}
