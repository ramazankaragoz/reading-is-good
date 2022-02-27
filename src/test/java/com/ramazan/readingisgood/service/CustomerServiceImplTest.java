package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.repository.AuthoritiesRepository;
import com.ramazan.readingisgood.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerServiceImplTest Unit Tests")
class CustomerServiceImplTest {


    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AuthoritiesRepository authoritiesRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer=new Customer();
        customer.setEmail("test@test.com");
        customer.setPassword(passwordEncoder.encode("test"));
        customer.setEnabled(Boolean.TRUE);
        customer.setName("test");
        customer.setSurname("test");
    }

    @Test
    void test_customer_when_save() {
        customerService.save(customer);
        verify(customerRepository,times(1)).save(customer);
    }
}