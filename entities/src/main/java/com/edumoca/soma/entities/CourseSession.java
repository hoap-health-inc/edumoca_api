package com.edumoca.soma.entities;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "COURSE_SESSION")
@Data
public class CourseSession{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SESSION_ID")
    private int sessionId;
    @OneToOne
    @JoinColumn(name = "PERIOD_ID")
    private Period period;
    @Column(name = "START_TIME")
    LocalTime startTime;
    @Column(name = "END_TIME")
    LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID")
    private Institution institution;
}
