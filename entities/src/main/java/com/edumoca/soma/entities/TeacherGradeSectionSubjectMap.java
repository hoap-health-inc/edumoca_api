package com.edumoca.soma.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TEACHER_GRADE_SECTION_SUBJECT_MAPPING",uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID","GRADE_SECTION_INSTITUTION_YEAR_MAP_ID","SUBJECT_ID"}))
@Setter
@Getter
public class TeacherGradeSectionSubjectMap extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEACHER_GRADE_SECTION_SUBJECT_MAPPING_ID")
	private int teacherGradeSectionSubjectMappingId;
	@ManyToOne
	@JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID",referencedColumnName = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
	private GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap;
	@ManyToOne
	@JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID")
	private Teacher teacher;
	@ManyToOne
	@JoinColumn(name = "SUBJECT_ID",referencedColumnName = "SUBJECT_ID")
	private Subject subject;
}
