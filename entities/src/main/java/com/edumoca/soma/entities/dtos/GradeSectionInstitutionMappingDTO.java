package com.edumoca.soma.entities.dtos;

import lombok.Data;

@Data
public class GradeSectionInstitutionMappingDTO {
    private int gradeSectionInstitutionMappingId;
    private String gradeName;
    private String sectionName;
    private String institutionName;
}
