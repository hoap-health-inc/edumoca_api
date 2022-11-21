package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InstitutionResponse {
    private int institutionId;
    private String institutionName;
}
