package com.edumoca.soma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ACADEMIC_YEAR",uniqueConstraints = {@UniqueConstraint(columnNames = {"START_DATE","END_DATE","INST_ID"})})
@Data
public class AcademicYear extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "START_DATE",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startDate;
    @Column(name="END_DATE",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endDate;
    @OneToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID",nullable = false)
    @JsonIgnore
    private Institution institution;
}
