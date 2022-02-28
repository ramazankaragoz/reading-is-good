package com.ramazan.readingisgood.repository.mapper;

import com.ramazan.readingisgood.dto.StatisticsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StatisticsRowMapper implements RowMapper<StatisticsDTO> {

    @Override
    public StatisticsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StatisticsDTO(rs.getDate("month"),rs.getInt("totalOrderCount"),rs.getInt("totalBookCount"),rs.getDouble("totalPurchasedAmount"));
    }
}
