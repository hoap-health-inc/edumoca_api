package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Period;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalTime;

@Data
public class CourseSessionRequest implements Serializable {
    private int sessionId;
    private Period periodIdRequest;
    LocalTime startTime;
    LocalTime endTime;
    private InstitutionIdRequest institutionIdRequest;
}
