package com.duo.duo.services;

import com.duo.duo.dto.AnswerDto.GetAnswersResponse;
import com.duo.duo.dto.AnswerDto.NewAnswerDto;
import com.duo.duo.dto.AnswerDto.NewAnswerResponseDto;

public interface AnswerService {
    public NewAnswerResponseDto postAnswer(NewAnswerDto data, Long idUser);
    public GetAnswersResponse getAnswers(Long questionId);
}
