package com.dungnt.healthclinic.service;


import com.dungnt.healthclinic.model.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> findAll();
    Optional<Question> findById(Long id) throws Exception;
    void save(Question question) throws Exception;
    void remove(Question question);
}
