package com.ramazan.readingisgood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public abstract class AbstractAuditBaseEntity extends AbstractBaseEntity {
    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    protected Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    protected String lastModifiedBy;

    @LastModifiedDate
    @Version
    @Column(name = "last_modified_date")
    @JsonIgnore
    protected Instant lastModifiedDate = Instant.now();

    @Column(name = "deleted")
    @JsonIgnore
    protected Boolean deleted = false;

    @PrePersist
    public void prePersist(){
        this.deleted=Boolean.FALSE;
    }
}
