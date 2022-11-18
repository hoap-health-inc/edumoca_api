package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;

@Data
public class AcademicYearRequest implements Serializable {
    @JsonIgnore
    private int academicYearId;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "UTC")
    private Calendar startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "UTC")
    private Calendar endDate;
    @NotNull
    private InstitutionIdRequest institutionIdRequest;
}
