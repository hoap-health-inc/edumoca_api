package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RoleRequest implements Serializable {
    @JsonIgnore
    private int roleId;
    @NotBlank(message = "Role Name is required")
    private String roleName;
    private InstitutionIdRequest institutionIdRequest;
}
