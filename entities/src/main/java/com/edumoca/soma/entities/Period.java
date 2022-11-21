package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

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
