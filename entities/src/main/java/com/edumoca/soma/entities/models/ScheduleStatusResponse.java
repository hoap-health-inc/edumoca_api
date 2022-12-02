package com.edumoca.soma.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleStatusResponse {
    private int scheduleStatusId;
    private int scheduleId;
    private String status;
    private Date startDate;
    private Date endDate;
}
