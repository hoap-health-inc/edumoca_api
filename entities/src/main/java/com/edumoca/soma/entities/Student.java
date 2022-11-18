package com.edumoca.soma.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@DiscriminatorValue(value = "STUDENT")
@Data
public class Student extends AppUser { 
	@ManyToOne
    @JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
	private GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping;
	@Column(name = "DOB")
    private LocalDate dateOfBirth;
	@Column(name = "LRN")
	private String lrn;
	@Column(name = "PSA_REG_NUMBER")
	private String psaRegNumber;
	@Column(name = "CITIZEN_SHIP")
	private String citizenShip;
	@Column(name = "RELIGION")
	private String religion;
	@Column(name = "PLACE_OF_BIRTH")
	private String placeOfBirth;
	@Column(name = "SCHOOL_LAST_ATTENDED")
	private String schoolLastAttended;
}
