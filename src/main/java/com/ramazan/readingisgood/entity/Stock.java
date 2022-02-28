package com.ramazan.readingisgood.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
@Where(clause = "deleted=false")
public class Stock extends AbstractAuditBaseEntity{

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "available",nullable = false)
    private Boolean isAvailable;
    @Column(name = "quantity",nullable = false)
    private Integer quantity;
}
