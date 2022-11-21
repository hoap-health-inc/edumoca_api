package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECT",uniqueConstraints = {@UniqueConstraint(columnNames = {"SUBJECT_NAME","INST_ID"})})
@Data
public class Subject extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    private int subjectId;
    @Column(name = "SUBJECT_NAME")
    private String subjectName;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID")
    private Institution institution;
}
