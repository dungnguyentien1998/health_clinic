package com.dungnt.healthclinic.controller;

import com.dungnt.healthclinic.model.ClinicService;
import com.dungnt.healthclinic.model.Question;
import com.dungnt.healthclinic.model.User;
import com.dungnt.healthclinic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {
    private QuestionService questionService;

    @Autowired
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
    public  ResponseEntity<Question> findQuestionById(@PathVariable("id") Long id) throws Exception {
        Optional<Question> question = questionService.findById(id);
        if (!question.isPresent()){
            return  new ResponseEntity<>(question.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create/question", method = RequestMethod.POST)
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) throws Exception {
        questionService.save(question);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/question/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") Long id,
                                           @RequestBody Question question) throws Exception {
        Optional<Question> currentQuestion = questionService.findById(id);
        if (!currentQuestion.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentQuestion.get().setQuestion(question.getQuestion());
        currentQuestion.get().setAnswers(question.getAnswers());

        questionService.save(currentQuestion.get());
        return new ResponseEntity<>(currentQuestion.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/question/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") Long id) throws Exception {
        Optional<Question> question = questionService.findById(id);
        if (!question.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionService.remove(question.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
