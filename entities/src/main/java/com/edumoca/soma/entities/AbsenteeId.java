package com.edumoca.soma.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AbsenteeId implements Serializable {
    private YearlyStudentGradeSectionEnrollment enrollment;
    private Date date;
}
