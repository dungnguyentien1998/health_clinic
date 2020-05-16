package com.dungnt.healthclinic.controller;

import com.dungnt.healthclinic.model.Question;
import com.dungnt.healthclinic.model.User;
import com.dungnt.healthclinic.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> findAllQuestions() {
        List<Question> questions = questionService.findAll();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Question> findQuestionById(@PathVariable("id") Integer id){
        Optional<Question> question = questionService.findById(id);
        if (!question.isPresent()){
            return  new ResponseEntity<>(question.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question.get(), HttpStatus.OK);
    }
}
