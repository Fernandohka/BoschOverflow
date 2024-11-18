package com.duo.duo.services;

import com.duo.duo.dto.QuestionDto.DeleteQuestionDto;
import com.duo.duo.dto.QuestionDto.GetQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionResponseDto;

public interface QuestionActionService {
    public PostQuestionResponseDto postQuestion(PostQuestionDto data);           
    public DeleteQuestionDto deleteQuestion(Long idQuestion, Long idUser);
    public GetQuestionDto getQuestion(Long id);
}