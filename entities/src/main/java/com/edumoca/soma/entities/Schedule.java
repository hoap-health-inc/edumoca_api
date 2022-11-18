package com.edumoca.soma.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@Table(name = "SCHEDULE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "scheduleId")
public class Schedule extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private int scheduleId;
    @Column(name = "SCHEDULE_NAME")
    private String scheduleName;
    @Column(name = "SCHEDULE_DATE")
    private LocalDateTime scheduleDateTime;
    @OneToOne
    @JoinColumn(name = "SESSION_ID")
    private CourseSession session;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @OneToMany(mappedBy = "schedule")
    private Set<ScheduleStatus> status;
    @ElementCollection(targetClass=DayOfWeek.class)
    @Enumerated(EnumType.STRING) 
    @CollectionTable(name="SCHEDULE_DAY_OF_WEEK")
    @Column(name="DAY_OF_WEEK")
    private Set<DayOfWeek> dayOfWeeks;
    @Column(name = "RECURRING")
    private boolean recurring;
    @Column(name = "FULL_DAY")
    private boolean fullDay;//HOLIDAY
    @ManyToOne
    @JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
    private GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping;
    @OneToOne
    @JoinColumn(name = "TEACHER_GRADE_SECTION_SUBJECT_MAPPING_ID")//,nullable = false
    private TeacherGradeSectionSubjectMapping teacherGradeSectionSubjectMapping;
    
//  @Column(name = "SCHEDULE_DURATION")
//  private Duration scheduleDuration;
//  @JoinTable(name = "SCHEDULE_COURSE_SESSION", joinColumns = {@JoinColumn(name = "SCHEDULE_ID")}, inverseJoinColumns = {@JoinColumn(name = "SESSION_ID")})
//  @JoinColumn(name = "SCHEDULE_ID",nullable = true)
}
