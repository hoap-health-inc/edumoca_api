package com.edumoca.soma.entities.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InstitutionResponse {
    private int institutionId;
    private String institutionName;
}
