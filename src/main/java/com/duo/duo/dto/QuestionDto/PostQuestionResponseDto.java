package com.duo.duo.dto.QuestionDto;

import java.util.ArrayList;

import com.duo.duo.model.Question;

public record PostQuestionResponseDto(
    Question question,
    ArrayList<String> messages
) {}
