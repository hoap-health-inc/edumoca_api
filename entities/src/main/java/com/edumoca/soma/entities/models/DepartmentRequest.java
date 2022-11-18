package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class DepartmentRequest implements Serializable {
    @JsonIgnore
    private int departmentId;
    @NotBlank(message = "Department Name is required")
    private String departmentName;
    private InstitutionIdRequest institutionIdRequest;
}
