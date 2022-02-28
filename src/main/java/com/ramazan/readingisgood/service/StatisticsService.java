package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.dto.StatisticsDTO;
import com.ramazan.readingisgood.util.OrderStatus;

import java.util.List;

public interface StatisticsService {

    List<StatisticsDTO> findMonthlyOrderStatistics(OrderStatus orderStatus);
}
