package com.duo.duo.services;

import com.duo.duo.model.Answer;
import com.duo.duo.model.Question;

public interface QuestionActionService {
    public void postQuestion(Question question);            //Adicionar questão
    public void postAnswer(Answer answer);                  //Adicionar resposta
    public void deleteQuestion(Long idQuestion);            //Deletar questão
    public void deleteAnswer(Long idAnswer);                //Deletar resposta
}