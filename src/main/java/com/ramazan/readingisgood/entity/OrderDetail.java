package com.ramazan.readingisgood.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_order_detail")
public class OrderDetail extends AbstractAuditBaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "book=" + book +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(((OrderDetail) o).getId(), that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
