package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.documents.Quiz;
import com.edumoca.soma.entities.dtos.QuizDto;
import com.edumoca.soma.entities.repositories.QuestionRepository;
import com.edumoca.soma.entities.repositories.QuizRepository;
import com.edumoca.soma.services.services.QuizService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Override
    public QuizDto createQuiz(Quiz quiz) {
        quiz.getQuestions().forEach(question->{
            questionRepository.save(question);
        });
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

}
