package com.edumoca.soma.entities.dtos;


import lombok.Data;

import java.util.Calendar;
@Data
public class AcademicYearDto {
    private int academicYearId;
    private Calendar startDate;
    private Calendar endDate;
}
