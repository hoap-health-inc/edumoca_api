package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

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
