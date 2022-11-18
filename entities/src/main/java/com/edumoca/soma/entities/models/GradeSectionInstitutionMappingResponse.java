package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GradeSectionInstitutionMappingResponse {
    int gradeSectionInstitutionMappingId;
    String gradeName;
    String sectionName;
    String institutionName;
}
