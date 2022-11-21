package com.edumoca.soma.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AbsenteeId implements Serializable {
    private YearlyStudentGradeSectionEnrollment enrollment;
    private Date date;
}
