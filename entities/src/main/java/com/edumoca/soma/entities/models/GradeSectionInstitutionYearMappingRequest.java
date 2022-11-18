package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class GradeSectionInstitutionYearMappingRequest implements Serializable {
    @JsonIgnore
    private int gradeSectionInstitutionYearMappingId;
    private GradeSectionInstitutionMappingIdRequest gradeSectionInstitutionMappingIdRequest;
    private AcademicYearIdRequest academicYearIdRequest;
}
