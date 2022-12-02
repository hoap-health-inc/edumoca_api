package com.edumoca.soma.entities.documents;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class AnswerKey {
//    private String answer;
//    private Map<Integer,Integer> matchingTypeAnswers=new HashMap<>(); // for match the following type answers
//    private Object[] positionalTypeAnswers;
//    private int answerId;

   // Map<Map<String,Choices>,Map<String,Choices>> matchFollowMap;
    //Map<String,Choices> choicesMap;

    List<Object> correctAnswerIndex; //teacher answered
    List<Object> answeredIndex;       //student answered
}
