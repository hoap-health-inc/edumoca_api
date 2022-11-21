package com.edumoca.soma.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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
