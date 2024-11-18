package com.duo.duo.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findByUserSpaceId(Long userSpaceId, PageRequest req);
}
