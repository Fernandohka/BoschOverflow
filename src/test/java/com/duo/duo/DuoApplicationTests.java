package com.duo.duo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.duo.duo.model.User;

import com.duo.duo.repositories.UserRepository;
import com.duo.duo.services.UserValidateService;

@SpringBootTest
public class DuoApplicationTests {

    @Autowired
    UserValidateService userValidateService;

	@Autowired
	UserRepository userRepository;

    
	@Test
	void validarSenhaPequena(){
		assertEquals(userValidateService.validatePassword("oi"), false);
		assertEquals(userValidateService.validatePassword("minhasenha1"), false);
		assertEquals(userValidateService.validatePassword("minhasenhaj"), false);
		assertEquals(userValidateService.validatePassword("12345678910"), false);
		assertEquals(userValidateService.validatePassword("#h*HR@cBp*z"), false);
	}

	@Test
	void validarSenhaSemNumero(){
		assertEquals(userValidateService.validatePassword("minhasenhasegura"), false);
		assertEquals(userValidateService.validatePassword("MinhaSenhaSegura"), false);
		assertEquals(userValidateService.validatePassword("#h*HR@cBp*z!XA@%"), false);
	}
	
	@Test
	void validarSenhaSemMaiuscula(){
		assertEquals(userValidateService.validatePassword("minhasenhasegura"), false);
		assertEquals(userValidateService.validatePassword("minhasenhasegura123"), false);
		assertEquals(userValidateService.validatePassword("#h*84@c6p*z!23@%"), false);
	}
	
	@Test
	void validarSenhaSemMinuscula(){
		assertEquals(userValidateService.validatePassword("MINHASENHASEGURA"), false);
		assertEquals(userValidateService.validatePassword("MINHASENHASEGURA123"), false);
		assertEquals(userValidateService.validatePassword("#H*84@C6P*Z!23@%"), false);
	}
	
	@Test
	void validarSenhaFuncional(){
		assertEquals(userValidateService.validatePassword("MinhaSenhaSegura123"), true);
		assertEquals(userValidateService.validatePassword("MINHASENHASEGURa123"), true);
		assertEquals(userValidateService.validatePassword("#H*84@c6P*z!23@%"), true);
	}

	@Test
	void validarEmail(){
		assertEquals(userValidateService.validateEmail("user@email.com"), true);
		assertEquals(userValidateService.validateEmail("user@email.com.br"), true);
		assertEquals(userValidateService.validateEmail("user.user@email.com"), true);
		assertEquals(userValidateService.validateEmail("user.user@email.com.br"), true);
		assertEquals(userValidateService.validateEmail("user.user.@email.com.br"), true);
		assertEquals(userValidateService.validateEmail("user@user@email.com"), false);
		assertEquals(userValidateService.validateEmail("user@email."), false);
		assertEquals(userValidateService.validateEmail("user@.com"), false);
		assertEquals(userValidateService.validateEmail("@email.com"), false);
		assertEquals(userValidateService.validateEmail("user@email"), false);
		assertEquals(userValidateService.validateEmail("user.com@email"), false);
		assertEquals(userValidateService.validateEmail("useremail.com"), false);
	}
	
	@Test
	void validarEdvPequeno(){
		assertEquals(userValidateService.validateEdv("1234567"), false);
		assertEquals(userValidateService.validateEdv("123456"), false);
	}

	@Test
	void validarEdvGrande(){
		assertEquals(userValidateService.validateEdv("123456789"), false);
		assertEquals(userValidateService.validateEdv("12345678910"), false);
	}

	@Test
	void validarEdvComLetra(){
		assertEquals(userValidateService.validateEdv("1234567a"), false);
		assertEquals(userValidateService.validateEdv("12C4567H"), false);
		assertEquals(userValidateService.validateEdv("meuedv"), false);
		assertEquals(userValidateService.validateEdv("MeuEdvTeste"), false);
		assertEquals(userValidateService.validateEdv("aBcDeFgH"), false);
	}
	
	@Test
	void validarEdvFuncional(){
		assertEquals(userValidateService.validateEdv("92904373"), true);
		assertEquals(userValidateService.validateEdv("92904320"), true);
	}

	@Test
	void adicionarUsuario(){
		// userRepository.tra
		// User new_user = new User("username", "password", "email@email.com", "12345678");
	}
}
