package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.dto.StatisticsDTO;
import com.ramazan.readingisgood.service.StatisticsService;
import com.ramazan.readingisgood.util.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Operation(summary = "Will serve customer’s monthly order statistics.")
    @GetMapping("/{orderStatus}")
    public ResponseEntity<List<StatisticsDTO>> findMonthlyOrderStatistics(@PathVariable("orderStatus") OrderStatus orderStatus){
        return new ResponseEntity<>(statisticsService.findMonthlyOrderStatistics(orderStatus), HttpStatus.OK);
    }
}
