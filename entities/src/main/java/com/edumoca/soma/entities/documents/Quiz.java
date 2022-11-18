package com.edumoca.soma.entities.documents;

import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.entities.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.List;

@Document
public class Quiz {
    @Id
    private int quizId;
    private Duration duration;
    private Schedule scheduleId;
    List<Question> questions;
    private Student student;

}
