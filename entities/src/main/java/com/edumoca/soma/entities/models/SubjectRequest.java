package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SubjectRequest implements Serializable {
    @JsonIgnore
    private int subjectId;
    @NotBlank(message = "Subject Name is required")
    private String subjectName;
    private InstitutionIdRequest institutionIdRequest;
}
