package com.duo.duo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.duo.duo.dto.Token;
import com.duo.duo.dto.AnswerDto.NewAnswerDto;
import com.duo.duo.dto.AnswerDto.NewAnswerResponseDto;
import com.duo.duo.services.AnswerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duo.duo.dto.AnswerDto.GetAnswersResponse;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;
    
    /*
     * Função de postar uma resposta, aqui, a função de "postAnswer" da Service, retorna uma Dto com result, para tratar os erros.  
    */
    @PostMapping("")
    public ResponseEntity<String> postAnswer(@RequestAttribute("token") Token token, @RequestBody NewAnswerDto data) {
        
        NewAnswerResponseDto response = answerService.postAnswer(data, token.getId());

        if (response.result() == 0)
            return new ResponseEntity<>( response.message(), HttpStatus.BAD_REQUEST);
    
        if (response.result() == 1)
            return new ResponseEntity<>( response.message(), HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(response.message(), HttpStatus.OK);
    }

    @GetMapping("{questionId}")
    public ResponseEntity<GetAnswersResponse> getAnswer(@PathVariable Long questionId) {

        GetAnswersResponse response = answerService.getAnswers(questionId);

        if (response.answersList() == null)
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
