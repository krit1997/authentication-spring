package com.example.authentication.spring.boots.entities;

import java.time.OffsetDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(updatable = false, nullable = false, length = 36)
    private String id;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "updated_by", nullable = true)
    private String updatedBy;

    @Column(name = "created_time", columnDefinition = "timestamptz", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(name = "updated_time", columnDefinition = "timestamptz")
    @org.hibernate.annotations.UpdateTimestamp
    private OffsetDateTime updatedTime;

    @Column(name = "deleted_date", columnDefinition = "timestamptz")
    private OffsetDateTime deletedDate;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
