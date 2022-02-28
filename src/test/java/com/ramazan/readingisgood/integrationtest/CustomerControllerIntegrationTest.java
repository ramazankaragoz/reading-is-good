package com.ramazan.readingisgood.integrationtest;

import com.ramazan.readingisgood.controller.CustomerController;
import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.exception.CustomerAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer=new Customer();
        customer.setEmail(UUID.randomUUID().toString().substring(0,5) +"@test.com");
        customer.setPassword("test");
        customer.setEnabled(Boolean.TRUE);
        customer.setName("test");
        customer.setSurname("test");
    }

    @Test
    public void test_customer_when_save(){

        ResponseEntity<Customer> customerResponse=customerController.save(customer);
        Assertions.assertTrue(customerResponse.getStatusCode().equals(HttpStatus.CREATED));

    }

    @Test
    public void test_exist_customer_when_save(){

        customer.setEmail("user@readingisgood.com");

        Assertions.assertThrows(CustomerAlreadyExistException.class,() -> {
            customerController.save(customer);
        });

    }
}
