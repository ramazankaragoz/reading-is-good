package com.ramazan.readingisgood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book extends AbstractAuditBaseEntity{

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "author",nullable = false)
    private String author;
    @Column(name = "price")
    private Double price;
}
