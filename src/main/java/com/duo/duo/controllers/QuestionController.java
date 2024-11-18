package com.duo.duo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.QuestionDto.DeleteQuestionDto;
import com.duo.duo.dto.Token;
import com.duo.duo.dto.QuestionDto.GetQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionResponseDto;
import com.duo.duo.services.QuestionActionService;



@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionActionService questionService;
    
    @GetMapping("/{space}")
    public String getPageableQuestions(@PathVariable String space, String page, String size) {

        return new String();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetQuestionDto> getQuestion(@RequestParam Long id) {

        GetQuestionDto question = questionService.getQuestion(id);

        if (!question.messages().isEmpty()) {
            return new ResponseEntity<>(question, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PostQuestionResponseDto> postQuestion(@RequestBody PostQuestionDto data) {
        
        PostQuestionResponseDto response = questionService.postQuestion(data);

        if (!response.messages().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@RequestAttribute("token") Token token, @PathVariable Long id) {

        DeleteQuestionDto response = questionService.deleteQuestion(id, token.getId());

        if (response.result() == 0) {
            return new ResponseEntity<>(response.message(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response.message(), HttpStatus.OK);
    }
}
