package com.edumoca.soma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PERIOD")
@Data
public class Period{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERIOD_ID")
	private int periodId;
    @Column(name = "PERIOD_NAME")
	private String periodName;
    @Column(name = "PERIOD_NO")
    private int periodNo;
}
