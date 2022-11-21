package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "GRADE",uniqueConstraints = {@UniqueConstraint(columnNames = {"GRADE_NAME","INST_ID"})})
public class Grade extends BaseEntity {
    @Id
    @Column(name = "GRADE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;
    @Column(name = "GRADE_NAME")
    private String gradeName;
    @Column(name = "GRADE_DESC")
    private String gradeDescription;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID")
    private Institution institution;
}

