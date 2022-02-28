package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.dto.CreateOrderDTO;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.service.OrderService;
import com.ramazan.readingisgood.util.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Will persist new order.The orderSatus contains the parameters for adding to cart and placing an order.orderStatus a necessary parameter to manage the stock.")
    @PostMapping("/{orderStatus}")
    public ResponseEntity<Order> newOrder(@RequestBody @Valid CreateOrderDTO createOrderDTO,
                                          @PathVariable("orderStatus") @NotNull OrderStatus orderStatus){
        return new ResponseEntity<>(orderService.newOrder(createOrderDTO,orderStatus), HttpStatus.CREATED);
    }

    @Operation(summary = "Will query order by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id")UUID id){
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "List orders by date interval ( endDate - startDate ).DateTimeFormat.ISO.DATE format is used as the format.(2022-06-28 - 2022-02-28)")
    @GetMapping("find-by-between/{endDate}/{startDate}")
    public ResponseEntity<List<Order>> findBetweenByStartDateAndEndDate(@PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                                                        @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date startDate){
        return new ResponseEntity<>(orderService.findBetweenByStartDateAndEndDate(endDate,startDate), HttpStatus.OK);
    }
}
