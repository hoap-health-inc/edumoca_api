package com.edumoca.soma.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT",uniqueConstraints = {@UniqueConstraint(columnNames = {"DEPT_NAME","INST_ID"})})
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
	private int deptId;
    @Column(name = "DEPT_NAME")
	private String deptName;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID")
    private Institution institution;
}
