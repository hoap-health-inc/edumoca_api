package com.edumoca.soma.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private Student student;
}
