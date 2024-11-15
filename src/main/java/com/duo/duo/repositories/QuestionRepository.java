package com.duo.duo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
}
