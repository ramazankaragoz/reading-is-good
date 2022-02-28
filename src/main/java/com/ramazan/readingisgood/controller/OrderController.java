package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.dto.CreateOrderDTO;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.service.OrderService;
import com.ramazan.readingisgood.util.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{orderStatus}")
    public ResponseEntity<Order> newOrder(@RequestBody @Valid CreateOrderDTO createOrderDTO,
                                          @PathVariable("orderStatus") @NotNull OrderStatus orderStatus){
        return new ResponseEntity<>(orderService.newOrder(createOrderDTO,orderStatus), HttpStatus.CREATED);
    }
}
