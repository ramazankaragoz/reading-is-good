package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.dto.CreateOrderDTO;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.util.OrderStatus;

public interface OrderService {

    Order newOrder(CreateOrderDTO createOrderDTO, OrderStatus orderStatus);
}
