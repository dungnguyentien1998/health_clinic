package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.Question;
import com.dungnt.healthclinic.repository.QuestionRepository;
import com.dungnt.healthclinic.repository.UserRepository;
import com.dungnt.healthclinic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public Optional<Question> findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Gia tri id null");
        }
        return questionRepository.findById(id);
    }

    @Override
    public void save(Question question) throws Exception {
        if (question == null) {
            throw new Exception("Doi tuong cau hoi null");
        }
        if (question.getQuestion() == null) {
            throw new Exception("Noi dung cau hoi null");
        }
        if (question.getAnswers() == null) {
            throw new Exception("Noi dung cac cau tra loi null");
        }
        questionRepository.save(question);
    }

    @Override
    public void remove(Question question) {
        questionRepository.delete(question);
    }
}
