package com.edumoca.soma.entities.dtos;

import lombok.Data;

import java.util.Calendar;

@Data
public class GradeSectionInstitutionYearMappingDTO {
    private int gradeSectionInstitutionYearMappingId;
    private String gradeName;
    private String sectionName;
    private String institutionName;
    private Calendar academicStartDate;
    private Calendar academicEndDate;

}
