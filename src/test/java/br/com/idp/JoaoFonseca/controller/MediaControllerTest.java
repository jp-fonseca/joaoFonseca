package br.com.idp.JoaoFonseca.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.idp.JoaoFonseca.dto.MediaDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MediaControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void mustListAllMedias() {
		webTestClient.get()
		.uri("/medias")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(MediaDto.class).returnResult();
	}
}
