package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.ScheduleStatus;
import com.edumoca.soma.entities.models.ScheduleStatusResponse;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class StudentScheduleDto {
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
    private Set<ScheduleStatusResponse> scheduleStatusSet;
}
