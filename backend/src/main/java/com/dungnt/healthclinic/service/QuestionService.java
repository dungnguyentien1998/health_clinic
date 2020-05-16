package com.dungnt.healthclinic.service;

import com.dungnt.healthclinic.model.Calendar;
import com.dungnt.healthclinic.model.Question;
import com.dungnt.healthclinic.model.User;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> findAll();
    Optional<Question> findById(Integer id);
    void save(Question question);
    void remove(Question question);
}
