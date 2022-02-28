package com.ramazan.readingisgood.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@Where(clause = "deleted=false")
public class Book extends AbstractAuditBaseEntity{

    @NotNull
    @NotEmpty
    @Column(name = "name",nullable = false)
    private String name;
    @NotNull
    @NotEmpty
    @Column(name = "author",nullable = false)
    private String author;
    @NotNull
    @NotEmpty
    @Column(name = "price")
    private Double price;
}
