package com.edumoca.soma.entities.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CourseSessionDto {
    private int courseSessionId;
    private String periodName;
    private int periodNo;
    private LocalTime startTime;
    private LocalTime endTime;
}
