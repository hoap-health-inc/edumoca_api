package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.QuestionTypes;
import com.edumoca.soma.entities.documents.AnswerKey;
import lombok.Data;

@Data
public class QuestionRequest {
    private String questionText;
    private QuestionTypes questionType;
    private AnswerKey answerKey;
    private TopicIdRequest topic;
    //private List<AnswersColumn> answersColumns;
}
