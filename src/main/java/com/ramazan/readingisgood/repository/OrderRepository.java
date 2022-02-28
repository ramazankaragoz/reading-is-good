package com.ramazan.readingisgood.repository;

import com.ramazan.readingisgood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<List<Order>> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date endDate, Date startDate);

    Page<Order> findAllByCustomerId(UUID fkCustomerId, Pageable pageable);

}
