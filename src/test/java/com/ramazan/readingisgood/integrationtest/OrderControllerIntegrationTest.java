package com.ramazan.readingisgood.integrationtest;

import com.ramazan.readingisgood.controller.OrderController;
import com.ramazan.readingisgood.dto.CreateOrderDTO;
import com.ramazan.readingisgood.dto.OrderDetailDTO;
import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.entity.Customer;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.entity.Stock;
import com.ramazan.readingisgood.repository.BookRepository;
import com.ramazan.readingisgood.repository.CustomerRepository;
import com.ramazan.readingisgood.repository.OrderRepository;
import com.ramazan.readingisgood.repository.StockRepository;
import com.ramazan.readingisgood.util.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderControllerIntegrationTest {

    @Autowired
    OrderController orderController;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;


    private CreateOrderDTO createOrderDTO;

    @BeforeEach
    void setUp() {

        Customer customer=new Customer();
        customer.setEmail(UUID.randomUUID().toString().substring(0,5) +"@test.com");
        customer.setPassword("test");
        customer.setEnabled(Boolean.TRUE);
        customer.setName("test");
        customer.setSurname("test");
        customerRepository.save(customer);

        Book book=new Book();
        book.setName(UUID.randomUUID().toString().substring(0,5) +"@test.com");
        book.setAuthor("ramazan");
        book.setPrice(Math.random());

        bookRepository.save(book);

        Stock stock=new Stock();
        stock.setBook(book);
        stock.setIsAvailable(Boolean.TRUE);
        stock.setQuantity(99999);

        stockRepository.save(stock);

        createOrderDTO=new CreateOrderDTO();
        OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
        orderDetailDTO.setQuantity(1);
        orderDetailDTO.setFkBookId(book.getId());
        createOrderDTO.setOrderDetail(Arrays.asList(orderDetailDTO));
        createOrderDTO.setFkCustomerId(customer.getId());
        createOrderDTO.setStartDate(new Date());
    }

    @Test
    void newOrder() {
        ResponseEntity<Order> orderResponseEntity=orderController.newOrder(createOrderDTO,OrderStatus.FULFILLED);
        Assertions.assertTrue(orderResponseEntity.getStatusCode().equals(HttpStatus.CREATED));
    }
}