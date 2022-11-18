package com.edumoca.soma.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

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
    private Institution institution;
}
