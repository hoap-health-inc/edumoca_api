package com.edumoca.soma.entities.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.edumoca.soma.entities.QuestionTypes;
import com.edumoca.soma.entities.Topic;

import lombok.Data;

@Document("question_bank")
@Data
public class Question {
    @Id
    private int id;
    private String questionText;
    private QuestionTypes questionType;
    private AnswerKey answer;
    private Topic topic;
    private List<AnswersColumn> answersColumns; // For match the following type answers


}
