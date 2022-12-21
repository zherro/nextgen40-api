package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<S extends GenericSearch, R extends GenericResponse>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String uuid;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    protected LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name="created_by")
    protected User createdBy;

    @ManyToOne
    @JoinColumn(name="updated_by")
    protected User updatedBy;

    @ManyToOne
    @JoinColumn(name="deleted_by")
    protected User deletedBy;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) default 'ACTIVE'")
    protected Status status;

    public abstract String entityName();

    public abstract R toResponse(ModelMapper mapper);
    public abstract S toSearchModel(ModelMapper mapper);

    @PrePersist
    protected  void beforePersist() {
        createdAt = LocalDateTime.now();
        uuid = UUID.randomUUID().toString();

    }

    @PreUpdate
    protected  void beforeUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
