package com.edumoca.soma.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @JsonIgnore
    private Institution institution;
}

