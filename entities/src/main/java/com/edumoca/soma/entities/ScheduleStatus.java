package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Data
@Entity
@Table(name = "SCHEDULE_STATUS")
public class ScheduleStatus{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleStatusId;
    @ManyToOne
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "SCHEDULE_ID")
    Schedule schedule;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
}
