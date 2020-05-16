package com.dungnt.healthclinic.repository;

import com.dungnt.healthclinic.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
