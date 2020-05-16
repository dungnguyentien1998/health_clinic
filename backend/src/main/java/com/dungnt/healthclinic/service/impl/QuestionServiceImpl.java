package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.Question;
import com.dungnt.healthclinic.repository.QuestionRepository;
import com.dungnt.healthclinic.repository.UserRepository;
import com.dungnt.healthclinic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    @Override
    public List<Question> findAll() {
        return (List<Question>) questionRepository.findAll();
    }
    @Override
    public Optional<Question> findById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void remove(Question question) {

    }
}
