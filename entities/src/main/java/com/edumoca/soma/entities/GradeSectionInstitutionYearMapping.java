package com.edumoca.soma.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GRADE_SECTION_INSTITUTION_YEAR_MAPPING",uniqueConstraints = @UniqueConstraint(columnNames = {"GRADE_SECTION_INSTITUTION_MAP_ID","ACADEMIC_YEAR_ID"}))
@Data
@EqualsAndHashCode(exclude = {"schedules","students"})
@ToString(exclude = {"schedules","students"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "gradeSectionInstitutionYearMapId")
public class GradeSectionInstitutionYearMapping extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
    private int gradeSectionInstitutionYearMapId;
    @ManyToOne
    @JoinColumn(name = "GRADE_SECTION_INSTITUTION_MAP_ID",referencedColumnName = "GRADE_SECTION_INSTITUTION_MAP_ID")
    private GradeSectionInstitutionMapping gradeSectionInstitutionMapping;
    @ManyToOne
    @JoinColumn(name = "ACADEMIC_YEAR_ID",referencedColumnName = "ID")
    private AcademicYear academicYear;
    @OneToMany(mappedBy = "gradeSectionInstitutionYearMapping")
    private Set<Schedule> schedules;
    @OneToMany(mappedBy = "gradeSectionInstitutionYearMapping")
    private Set<Student> students = new HashSet<>();
}
