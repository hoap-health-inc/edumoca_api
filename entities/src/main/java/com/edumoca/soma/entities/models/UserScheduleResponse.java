package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class UserScheduleResponse {
    int userId;
    String loginId;
    String firstName;
    String middleName;
    String lastName;
    int scheduleId;
    String scheduleName;
    Date startDate;
    Date endDate;
    int sessionId;
    LocalTime startTime;
    LocalTime endTime;
    String periodName;
    int teacherId;
    String teacherName;
    int subjectId;
    String subjectName;
}
