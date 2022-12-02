package com.edumoca.soma.entities.documents;

import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.edumoca.soma.entities.QuestionTypes;
import com.edumoca.soma.entities.Topic;

@Document(collection = "question_bank")
@Data
public class Question {
    @Id
    private String id;
    private String questionText;
    private QuestionTypes questionType;
    private AnswerKey answerKey;
    private Topic topic;
    //private List<AnswersColumn> answersColumns; // For match the following type answers

}
