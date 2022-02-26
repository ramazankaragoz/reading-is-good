package com.ramazan.readingisgood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authorities extends AbstractAuditBaseEntity{

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @Column(name = "authority",nullable = false)
    private String authority;
}
