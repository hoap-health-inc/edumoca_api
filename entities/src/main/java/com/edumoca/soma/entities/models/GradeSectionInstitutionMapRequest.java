package com.edumoca.soma.entities.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class GradeSectionInstitutionMapRequest implements Serializable {
    @JsonIgnore
    private int gradeSectionInstitutionMappingId;
    private GradeIdRequest gradeIdRequest;
    private SectionIdRequest sectionIdRequest;
    private InstitutionIdRequest institutionIdRequest;
}
