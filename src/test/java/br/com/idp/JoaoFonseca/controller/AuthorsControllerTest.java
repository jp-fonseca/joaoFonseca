package br.com.idp.JoaoFonseca.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.idp.JoaoFonseca.dto.AuthorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthorsControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void mustListAllAuthors() {
		webTestClient.get()
		.uri("/authors")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(AuthorDto.class).returnResult();
	}
	

}
