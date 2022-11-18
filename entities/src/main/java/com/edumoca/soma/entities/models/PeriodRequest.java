package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PeriodRequest implements Serializable {
    @JsonIgnore
    private int periodId;
    @NotBlank(message = "Period Name is required")
    private String periodName;
    private int periodNo;
}
