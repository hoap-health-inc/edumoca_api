package com.edumoca.soma.entities.documents;

import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.entities.Subject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "quiz")
@Data
public class Quiz {
    @Id
    private String quizId;
    private String title;
    private List<Instruction> instructions;
    private String description; //bit wise description
    private Schedule schedule;
    private List<Question> questions;
    private Subject subject;
    private int maxPoints;
}
