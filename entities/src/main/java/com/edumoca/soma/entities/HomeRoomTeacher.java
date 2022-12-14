package com.edumoca.soma.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@DiscriminatorValue(value = "HOMEROOMTEACHER")
@Data
public class HomeRoomTeacher extends Teacher{
	@OneToOne
    @JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
	private GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping;
}
