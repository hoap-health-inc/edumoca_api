package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class GradeRequest implements Serializable {
    @JsonIgnore
    private int gradeId;
    @NotBlank(message = "Grade Name is required")
    private String gradeName;
    private String gradeDescription;
    private InstitutionIdRequest institutionIdRequest;
}
