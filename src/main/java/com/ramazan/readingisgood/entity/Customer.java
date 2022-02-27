package com.ramazan.readingisgood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@Where(clause = "deleted=false")
public class Customer extends AbstractAuditBaseEntity{

    @Email
    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "password",nullable = false)
    private String password;

    @NotNull
    @NotEmpty
    @Column(name = "name",nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname",nullable = false)
    private String surname;

    @NotNull
    @Column(name = "enabled",nullable = false)
    private Boolean enabled;
}
