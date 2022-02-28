package com.ramazan.readingisgood.entity;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authorities extends AbstractAuditBaseEntity{

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "authority",nullable = false)
    private String authority;
}
