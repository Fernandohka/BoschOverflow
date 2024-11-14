package com.duo.duo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.duo.duo.services.SpaceActionsService;
import com.duo.duo.services.UserService;
import com.duo.duo.services.UserValidateService;
import com.duo.duo.services.implementations.SpaceActionImplementation;
import com.duo.duo.services.implementations.UserImplementation;
import com.duo.duo.services.implementations.UserValidateImplementation;

@Configuration
public class DepedencyConfiguration {
    
    // @Bean
    // @Scope("singleton")                  //CRIA UM UNICO OBJETO PARA TODO O PROGRAMA 
    // @Scope("prototype")                  //TODA VEZ QUE PRECISAR DE UM OBJETO, ELE CRIA UM NOVO
    // @Scope("request")                    //PARA UMA ÚNICA REQUISIÇÃO ELE USA O MESMO OBJETO
    // @Scope("session")                    //O OBJETO VAI SAER USADO PARA TUDO DO USUARIO SELECIONAODI
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
    
}
