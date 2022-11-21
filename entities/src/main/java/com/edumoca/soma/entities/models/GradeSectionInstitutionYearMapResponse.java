package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;

@AllArgsConstructor
@Getter
public class GradeSectionInstitutionYearMapResponse {
    int gradeSectionInstitutionYearMappingId;
    String gradeName;
    String sectionName;
    String institutionName;
    Calendar startDate;
    Calendar endDate;
}
