package com.duo.duo.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    public ArrayList<Answer> findByQuestionId(Long questionId);
}
