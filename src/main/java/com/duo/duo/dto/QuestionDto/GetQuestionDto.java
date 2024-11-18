package com.duo.duo.dto.QuestionDto;

import java.util.ArrayList;

public record GetQuestionDto(
    String description,
    Long userId,
    ArrayList<String> messages
) {}
