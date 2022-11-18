package com.edumoca.soma.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;


@Entity
@Table(name = "GRADE_SECTION_INSTITUTION_MAPPING",uniqueConstraints = @UniqueConstraint(columnNames = {"GRADE_ID","SECTION_ID","INST_ID"}))
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "gradeSectionInstitutionMappingId")
public class GradeSectionInstitutionMapping{
    @Id
    @Column(name = "GRADE_SECTION_INSTITUTION_MAP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeSectionInstitutionMappingId;
    @ManyToOne
    @JoinColumn(name = "GRADE_ID", referencedColumnName = "GRADE_ID",nullable = false)
    private Grade grade;
    @ManyToOne
    @JoinColumn(name = "SECTION_ID", referencedColumnName = "SECTION_ID",nullable = false)
    private Section section;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID",nullable = false)
    private Institution institution;
}
