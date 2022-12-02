package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.documents.Quiz;
import com.edumoca.soma.entities.dtos.QuizDto;
import com.edumoca.soma.entities.models.QuizRequest;
import com.edumoca.soma.services.services.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizRequest quizRequest) throws JsonProcessingException {
       return new ResponseEntity<>(quizService.createQuiz(modelMapper.map(quizRequest,Quiz.class)), HttpStatus.CREATED);
    }
}
