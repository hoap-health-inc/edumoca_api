package com.edumoca.soma.entities.models;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class StudentScheduleDTO {
    private int scheduleId;
    private String scheduleName;
    private Date startDate;
    private Date endDate;
    private int sessionId;
    private String periodName;
    private LocalTime startTime;
    private LocalTime endTime;
    private int teacherId;
    private String teacherName;
    private int subjectId;
    private String subjectName;
    private List<DayOfWeek> dayOfWeekSet;
}
