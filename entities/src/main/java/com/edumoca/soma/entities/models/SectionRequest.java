package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SectionRequest implements Serializable {
    @JsonIgnore
    private int sectionId;
    @NotBlank(message = "Section Name is required")
    private String sectionName;
    private String description;
    private InstitutionIdRequest institutionIdRequest;
}
