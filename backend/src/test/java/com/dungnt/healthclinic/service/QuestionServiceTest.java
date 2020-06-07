package com.dungnt.healthclinic.service;

import com.dungnt.healthclinic.model.Question;
import com.dungnt.healthclinic.repository.QuestionRepository;
import com.dungnt.healthclinic.service.impl.QuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceTest {
    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionService questionService;

    @Before
    public void setUp() {
        questionRepository = mock(QuestionRepository.class);
        questionService = new QuestionServiceImpl(questionRepository);
    }

    @Test
    public void testFindByIdWhenIdIsNull() {
        Long id = null;
        try {
            questionService.findById(id);
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    public void testFindByIdWhenTrue() throws Exception {
        Long id = 1L;
        Question question = new Question();
        Optional<Question> optionalQuestion = Optional.of(question);
        when(questionRepository.findById(id)).thenReturn(optionalQuestion);
        Optional<Question> questionTest = questionService.findById(id);
        assertNotNull(questionTest);
    }

    @Test
    public void testSaveWhenQuestionIsNull() {
        Question question = null;
        try {
            questionService.save(question);
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    public void testSaveWhenQuestionOrAnswerIsNull() {
        Question question = new Question();
        try {
            questionService.save(question);
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

}