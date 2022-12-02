package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.documents.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question,String> {
}
