package com.edumoca.soma.entities.dtos;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ScheduleDto {
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
    //private Set<DayOfWeek> dayOfWeeks;
}
