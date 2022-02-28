package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.dto.CreateOrderDTO;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.util.OrderStatus;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    Order newOrder(CreateOrderDTO createOrderDTO, OrderStatus orderStatus);

    Order findById(UUID fkOrderId);
    List<Order> findBetweenByStartDateAndEndDate(Date endDate, Date startDate);

}
