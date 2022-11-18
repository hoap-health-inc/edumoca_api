package com.edumoca.soma.entities.documents;

import java.util.HashMap;
import java.util.Map;

public class AnswerKey {
    private String answer;
    private Map<Integer,Integer> matchingTypeAnswers=new HashMap<>(); // for match the following type answers
    private Object[] positionalTypeAnswers;
    private int answerId;
}
