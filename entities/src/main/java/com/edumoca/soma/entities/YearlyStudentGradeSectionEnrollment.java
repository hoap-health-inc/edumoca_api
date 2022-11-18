package com.edumoca.soma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "YEARLY_STUDENT_GRADE_SECTION_ENROLLMENT", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID","GRADE_SECTION_INSTITUTION_YEAR_MAP_ID"}))
@Setter
@Getter
public class YearlyStudentGradeSectionEnrollment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENROLLMENT_ID")
    private int enrollmentId;
    @ManyToOne
    @JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID", referencedColumnName = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
    private GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private Student student;
}
