package com.duo.duo.services;

import java.util.ArrayList;

import java.util.ArrayList;

import com.duo.duo.dto.Token;
import com.duo.duo.dto.QuestionDto.DeleteQuestionDto;
import com.duo.duo.dto.QuestionDto.GetQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionResponseDto;
import com.duo.duo.model.Question;

public interface QuestionActionService {
    public PostQuestionResponseDto postQuestion(PostQuestionDto data, Token token);           
    public DeleteQuestionDto deleteQuestion(Long idQuestion, Long idUser);
    public GetQuestionDto getQuestion(Long id);
    public ArrayList<Question> getAllQuestions(Long spaceId, Integer page, Integer limit);

}