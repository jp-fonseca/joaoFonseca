package br.com.idp.JoaoFonseca.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.idp.JoaoFonseca.form.ReplyForm;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ReplyControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void givenValidReplyFormAndValidTopicId_whenRegisterReply_thenMustReturnCreated() {
		ReplyForm form = new ReplyForm();
		form.setAuthorId("1");
		form.setDescription("Valid description replyform.");
		
		webTestClient.post()
		.uri("/topics/1")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),ReplyForm.class)
		.exchange()
		.expectStatus()
		.isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()
		.jsonPath("$.description").isNotEmpty()
		.jsonPath("$.description").isEqualTo("Valid description replyform.")
		;
	}
	
	@Test
	void givenValidReplyFormAndTopicClosedId_whenRegisterReply_thenMustReturnBadRequest() {
		ReplyForm form = new ReplyForm();
		form.setAuthorId("1");
		form.setDescription("Valid description replyform.");
		
		webTestClient.post()
		.uri("/topics/3")  // This is a closed topic, so it can't be replied.
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),ReplyForm.class)
		.exchange()
		.expectStatus()
		.isBadRequest()
		;
	}
	
	@Test
	void givenValidReplyFormAndInvalidTopicId_whenRegisterReply_thenMustReturnInternalServerError() {
		ReplyForm form = new ReplyForm();
		form.setAuthorId("1");
		form.setDescription("Valid description replyform.");
		
		webTestClient.post()
		.uri("/topics/5")  // This is a inexistent topic, so it can't be replied.
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),ReplyForm.class)
		.exchange()
		.expectStatus()
		.is5xxServerError()
		;
	}
	
	@Test
	void givenInvalidReplyFormAndValidTopicId_whenRegisterReply_thenMustReturnBadRequest() {
		ReplyForm form = new ReplyForm();
	
		webTestClient.post()
		.uri("/topics/1") 
		.body(Mono.just(form),ReplyForm.class)
		.exchange()
		.expectStatus()
		.isBadRequest()
		;
	}
	
	@Test
	void givenInvalidReplyFormAndInvalidTopicId_whenRegisterReply_thenMustReturnBadRequest() {
		
		ReplyForm form = new ReplyForm();
		form.setAuthorId("");
		form.setDescription("");
		
		webTestClient.post()
		.uri("/topics/5") 
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),ReplyForm.class)
		.exchange()
		.expectStatus()
		.isBadRequest()
		;
	}
}
