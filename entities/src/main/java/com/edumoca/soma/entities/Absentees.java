package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ABSENTEES")
@Data
@IdClass(AbsenteeId.class)
public class Absentees {
    @Id
    @ManyToOne
    @JoinColumn(name = "ENROLLMENT_ID", referencedColumnName = "ENROLLMENT_ID")
    private YearlyStudentGradeSectionEnrollment enrollment;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    @Id
    private Date date;
}
