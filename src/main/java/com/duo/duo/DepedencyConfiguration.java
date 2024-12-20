package com.duo.duo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.duo.duo.dto.Token;
import com.duo.duo.services.AnswerService;
import com.duo.duo.services.EncoderService;
import com.duo.duo.services.JwtService;
import com.duo.duo.services.QuestionActionService;
import com.duo.duo.services.SpaceActionsService;
import com.duo.duo.services.UserAuthService;
import com.duo.duo.services.UserService;
import com.duo.duo.services.UserValidateService;
import com.duo.duo.services.implementations.AnswerImplementation;
import com.duo.duo.services.implementations.EncoderImplementation;
import com.duo.duo.services.implementations.JwtImplementation;
import com.duo.duo.services.implementations.QuestionActionImplementation;
import com.duo.duo.services.implementations.SpaceActionImplementation;
import com.duo.duo.services.implementations.UserAuthImplementation;
import com.duo.duo.services.implementations.UserImplementation;
import com.duo.duo.services.implementations.UserValidateImplementation;

@Configuration
public class DepedencyConfiguration {
    
    // * @Bean
    // * @Scope("singleton")                  // CRIA UM UNICO OBJETO PARA O PROGRAMA INTEIRO
    // * @Scope("prototype")                  // TODA VEZ QUE PRECISAR DE UM OBJETO, ELE CRIA UM NOVO
    // * @Scope("request")                    // PARA UMA ÚNICA REQUISIÇÃO ELE USA O MESMO OBJETO
    // * @Scope("session")                    // O OBJETO VAI SAER USADO PARA TUDO DO USUARIO SELECIONADO

    // public service service(){
    //     return new implementation();
    // }
    
    @Bean
    @Scope("singleton")
    public UserValidateService userValidateService(){
        return new UserValidateImplementation();
    }

    @Bean
    public UserService userService(){
        return new UserImplementation();
    }

    @Bean
    public SpaceActionsService spaceActionsService() {
        return new SpaceActionImplementation();
    }

    @Bean
    public EncoderService encoderService() {
        return new EncoderImplementation();
    }

    @Bean
    public JwtService<Token> jwtService() {
        return new JwtImplementation();
    }

    @Bean
    public UserAuthService authService() {
        return new UserAuthImplementation();
    }

    @Bean
    public QuestionActionService questionService() {
        return new QuestionActionImplementation();
    }

    @Bean
    public AnswerService answerService() {
        return new AnswerImplementation();
    }
}
