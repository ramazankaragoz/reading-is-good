package com.ramazan.readingisgood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramazan.readingisgood.util.OrderStatus;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_order")
@Where(clause = "deleted=false")
public class Order extends AbstractAuditBaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false)
    private OrderStatus orderStatus;

    @NotNull
    @Column(name = "total_amount",nullable = false)
    private Double totalAmount;

    @NotNull
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails=new ArrayList<>(0);

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Override
    public String toString() {
        return "Order{" +
                ", orderStatus=" + orderStatus +
                ", totalAmount=" + totalAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return id == order.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
