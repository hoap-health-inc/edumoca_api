package com.edumoca.soma.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity{
    @UpdateTimestamp
    @Column(name = "LAST_MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "UTC")
    protected Date lastModifiedOn;
    @CreationTimestamp
    @Column(name = "CREATED_ON",updatable = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "UTC")
    protected Date createdOn;
}
