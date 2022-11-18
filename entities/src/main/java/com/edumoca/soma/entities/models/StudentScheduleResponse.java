package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class StudentScheduleResponse {
    int scheduleId;
    String scheduleName;
    LocalDateTime scheduleDateTime;
    String periodName;
    int periodNo;
    Date startDate;
    Date endDate;
    int sessionId;
    LocalTime startTime;
    LocalTime endTime;
    //Set<ScheduleStatus> status;
    boolean recurring;
    boolean fullDay;//HOLIDAY
    int teacherId;
    String teacherName;
    int subjectId;
    String subjectName;
   // Set<DayOfWeek> dayOfWeeks;
}
