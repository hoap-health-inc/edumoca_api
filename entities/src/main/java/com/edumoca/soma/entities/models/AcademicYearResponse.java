package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;

@AllArgsConstructor
@Getter
public class AcademicYearResponse {
    int academicYearId;
    Calendar startDate;
    Calendar endDate;
}
