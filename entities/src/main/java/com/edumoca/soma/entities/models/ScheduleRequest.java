package com.edumoca.soma.entities.models;

import lombok.Data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
public class ScheduleRequest implements Serializable {
    private int scheduleId;
    private String scheduleName;
    private LocalDateTime scheduleDateTime;
    private CourseSessionIdRequest courseSessionIdRequest;
    private Date startDate;
    private Date endDate;
    //private Set<ScheduleStatus> status;
    private Set<DayOfWeek> dayOfWeeks;
    private boolean recurring;
    private boolean fullDay;//HOLIDAY
    private GradeSectionInstitutionYearMappingIdRequest gradeSectionInstitutionYearMappingIdRequest;
    private TeacherGradeSectionSubjectMappingIdRequest teacherGradeSectionSubjectMappingIdRequest;
}
