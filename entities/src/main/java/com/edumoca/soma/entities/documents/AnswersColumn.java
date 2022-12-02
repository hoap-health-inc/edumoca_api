package com.edumoca.soma.entities.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

public class AnswersColumn {
    List<AnswerOption> answers = new ArrayList<>();
}
