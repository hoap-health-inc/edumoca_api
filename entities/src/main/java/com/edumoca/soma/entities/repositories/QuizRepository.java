package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.documents.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz,String> {
}
