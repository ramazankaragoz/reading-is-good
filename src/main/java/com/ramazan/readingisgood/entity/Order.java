package com.ramazan.readingisgood.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ramazan.readingisgood.util.OrderStatus;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true,exclude = {"orderDetails"})
@ToString(callSuper = true,exclude = {"orderDetails"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_order")
@Where(clause = "deleted=false")
public class Order extends AbstractAuditBaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false)
    private OrderStatus orderStatus;

    @NotNull
    @Column(name = "total_amount",nullable = false)
    private Double totalAmount;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderDetail> orderDetails=new ArrayList<>(0);

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
}
