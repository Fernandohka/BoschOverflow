package com.duo.duo.services;

import com.duo.duo.model.Answer;
import com.duo.duo.model.Question;

public interface QuestionActionService {

    public void postQuestion(Question question);
    public void postAnswer(Answer answer);
    public void deleteQuestion(Long idQuestion);
    public void deleteAnswer(Long idAnswer);
}
