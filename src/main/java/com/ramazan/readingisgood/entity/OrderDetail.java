package com.ramazan.readingisgood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail extends AbstractAuditBaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @ManyToOne
    private Order order;
}
