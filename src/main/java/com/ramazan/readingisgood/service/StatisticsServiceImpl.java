package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.dto.StatisticsDTO;
import com.ramazan.readingisgood.repository.mapper.StatisticsRowMapper;
import com.ramazan.readingisgood.util.OrderStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StatisticsServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<StatisticsDTO> findMonthlyOrderStatistics(OrderStatus orderStatus) {
        StringBuilder sql=new StringBuilder();
        sql.append("select count(bo.id) as totalOrderCount, ");
        sql.append("sum(bo.total_amount)                     as totalPurchasedAmount,");
        sql.append("sum(bod.quantity)                        as totalBookCount,");
        sql.append("DATE_TRUNC('month', bo.start_date)       as month ");
        sql.append("from book_order bo full outer join book_order_detail bod on bo.id = bod.order_id ");
        sql.append("where bo.order_status = :orderStatus GROUP BY DATE_TRUNC('month', bo.start_date)");

        var mapSqlParameterSource=new MapSqlParameterSource();

        mapSqlParameterSource.addValue("orderStatus",orderStatus.name());

        return namedParameterJdbcTemplate.query(sql.toString(), mapSqlParameterSource, new StatisticsRowMapper());
    }
}
