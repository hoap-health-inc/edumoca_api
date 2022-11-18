package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class CourseSessionResponse {
     int sessionId;
     String periodName;
     int periodNo;
     LocalTime startTime;
     LocalTime endTime;
}
