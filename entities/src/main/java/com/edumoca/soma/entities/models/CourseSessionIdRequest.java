package com.edumoca.soma.entities.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseSessionIdRequest implements Serializable {
    private int courseSessionId;
}
