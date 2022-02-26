package com.ramazan.readingisgood.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@Getter
public abstract class AbstractBaseEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected UUID id;
}
