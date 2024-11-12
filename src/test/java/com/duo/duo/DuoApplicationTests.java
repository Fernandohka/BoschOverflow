package com.duo.duo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DuoApplicationTests {

	@Test
	void contextLoads() {
		
	}

	@Test
	void validarSenhaPequena(){
		assertEquals(UserValidateImplementation.validatePassword("oi"), false);
		assertEquals(UserValidateImplementation.validatePassword("minhasenha1"), false);
		assertEquals(UserValidateImplementation.validatePassword("minhasenhaj"), false);
		assertEquals(UserValidateImplementation.validatePassword("12345678910"), false);
		assertEquals(UserValidateImplementation.validatePassword("#h*HR@cBp*z"), false);
	}

	@Test
	void validarSenhaSemNumero(){
		assertEquals(UserValidateImplementation.validatePassword("minhasenhasegura"), false);
		assertEquals(UserValidateImplementation.validatePassword("MinhaSenhaSegura"), false);
		assertEquals(UserValidateImplementation.validatePassword("#h*HR@cBp*z!XA@%"), false);
	}
	
	@Test
	void validarSenhaSemMaiuscula(){
		assertEquals(UserValidateImplementation.validatePassword("minhasenhasegura"), false);
		assertEquals(UserValidateImplementation.validatePassword("minhasenhasegura123"), false);
		assertEquals(UserValidateImplementation.validatePassword("#h*84@c6p*z!23@%"), false);
	}
	
	@Test
	void validarSenhaSemMinuscula(){
		assertEquals(UserValidateImplementation.validatePassword("MINHASENHASEGURA"), false);
		assertEquals(UserValidateImplementation.validatePassword("MINHASENHASEGURA123"), false);
		assertEquals(UserValidateImplementation.validatePassword("#H*84@C6P*Z!23@%"), false);
	}
	
	@Test
	void validarSenhaFuncional(){
		assertEquals(UserValidateImplementation.validatePassword("MinhaSenhaSegura123"), true);
		assertEquals(UserValidateImplementation.validatePassword("MINHASENHASEGURa123"), true);
		assertEquals(UserValidateImplementation.validatePassword("#H*84@c6P*z!23@%"), true);
	}

	@Test
	void validarEmail(){
		assertEquals(UserValidateImplementation.validateEmail("user@email.com"), true);
		assertEquals(UserValidateImplementation.validateEmail("user@email.com.br"), true);
		assertEquals(UserValidateImplementation.validateEmail("user.user@email.com"), true);
		assertEquals(UserValidateImplementation.validateEmail("user.user@email.com.br"), true);
		assertEquals(UserValidateImplementation.validateEmail("user.user.@email.com.br"), true);
		assertEquals(UserValidateImplementation.validateEmail("user@user@email.com"), false);
		assertEquals(UserValidateImplementation.validateEmail("user@email."), false);
		assertEquals(UserValidateImplementation.validateEmail("user@.com"), false);
		assertEquals(UserValidateImplementation.validateEmail("@email.com"), false);
		assertEquals(UserValidateImplementation.validateEmail("user@email"), false);
		assertEquals(UserValidateImplementation.validateEmail("user.com@email"), false);
		assertEquals(UserValidateImplementation.validateEmail("useremail.com"), false);
	}
	
	@Test
	void validarEdvPequeno(){
		assertEquals(UserValidateImplementation.validateEdv("1234567"), false);
		assertEquals(UserValidateImplementation.validateEdv("123456"), false);
	}

	@Test
	void validarEdvGrande(){
		assertEquals(UserValidateImplementation.validateEdv("123456789"), false);
		assertEquals(UserValidateImplementation.validateEdv("12345678910"), false);
	}

	@Test
	void validarEdvComLetra(){
		assertEquals(UserValidateImplementation.validateEdv("1234567a"), false);
		assertEquals(UserValidateImplementation.validateEdv("12C4567H"), false);
		assertEquals(UserValidateImplementation.validateEdv("meuedv"), false);
		assertEquals(UserValidateImplementation.validateEdv("MeuEdvTeste"), false);
		assertEquals(UserValidateImplementation.validateEdv("aBcDeFgH"), false);
	}
	
	@Test
	void validarEdvFuncional(){
		assertEquals(UserValidateImplementation.validateEdv("92904373"), true);
		assertEquals(UserValidateImplementation.validateEdv("92904320"), true);
	}
}
