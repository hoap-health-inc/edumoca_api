package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class InstitutionRequest implements Serializable {
    @JsonIgnore
    private int institutionId;
    @NotBlank(message = "Institution Name is required")
    private String institutionName;
    private String description;
}
