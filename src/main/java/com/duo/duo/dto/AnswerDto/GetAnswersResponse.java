package com.duo.duo.dto.AnswerDto;

import java.util.List;

import com.duo.duo.model.Answer;

public record GetAnswersResponse(
    List<Answer> answersList,
    String message
) {}
