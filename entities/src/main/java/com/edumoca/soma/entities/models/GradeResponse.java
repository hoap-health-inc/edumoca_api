package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GradeResponse {
    int gradeId;
    String gradeName;
    String gradeDescription;
}
