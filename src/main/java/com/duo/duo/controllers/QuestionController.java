package com.duo.duo.controllers;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.QuestionDto.DeleteQuestionDto;
import com.duo.duo.dto.QuestionDto.GetQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionDto;
import com.duo.duo.dto.QuestionDto.PostQuestionResponseDto;
import com.duo.duo.dto.Token;
import com.duo.duo.model.Question;
import com.duo.duo.services.QuestionActionService;



@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionActionService questionService;
    
    /*
     * Função de pegar as perguntas pelo space 
     * Note que a URL é diferente da que está no quadro. Isso foi aprovado pelo Trevis 👍
     ! AVISO, NÃO HÁ TRATAMENTOS DE ERROS AQUI, CASO DÊ ERRO, PODE ALTERAR OU NOS AVISAR
    */
    @GetMapping("/by-space/{space}")
    public ResponseEntity<ArrayList<Question>> getPageableQuestions(@PathVariable Long spaceId, Integer page, Integer size) {

        var questions = questionService.getAllQuestions(spaceId, page, size);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /*
     * Função de pegar apenas uma pergunta 
     * Note que a URL é diferente da que está no quadro. Também aprovado pelo Trevis
    */
    @GetMapping("/by-id/{id}")
    public ResponseEntity<GetQuestionDto> getQuestion(@PathVariable Long id) {

        GetQuestionDto question = questionService.getQuestion(id);

        if (!question.messages().isEmpty()) {
            return new ResponseEntity<>(question, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question, HttpStatus.OK);
    }


    /*
     * Post de uma pergunta 
    */
    @PostMapping("")
    public ResponseEntity<PostQuestionResponseDto> postQuestion(@RequestAttribute("token") Token token, @RequestBody PostQuestionDto data) {
        
        PostQuestionResponseDto response = questionService.postQuestion(data, token);

        if (!response.messages().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Essa função deleta uma pergunta 
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@RequestAttribute("token") Token token, @PathVariable Long id) {

        DeleteQuestionDto response = questionService.deleteQuestion(id);

        if (response.result() == 0) {
            return new ResponseEntity<>(response.message(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response.message(), HttpStatus.OK);
    }
}
