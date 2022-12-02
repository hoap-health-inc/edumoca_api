package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.documents.Instruction;
import lombok.Data;

import java.util.List;

@Data
public class QuizRequest {
    private String title;
    private List<Instruction> instructions;
    private ScheduleIdRequest schedule;
    private List<QuestionRequest> questions;
    private SubjectIdRequest subject;
    private double maxPoints;
}
