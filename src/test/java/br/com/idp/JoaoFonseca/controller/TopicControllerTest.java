package br.com.idp.JoaoFonseca.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.idp.JoaoFonseca.dto.DetailedTopicDto;
import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import reactor.core.publisher.Mono;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TopicControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void mustListAllTopics() {
		webTestClient.get()
		.uri("/topics")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBodyList(TopicDto.class).returnResult();
	}
	
	@Test
	void givenValidTopicId_whenDetailTopic_mustDetailATopicWithItReplies() {
		
		webTestClient.get()
		.uri("/topics/1")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody(DetailedTopicDto.class).returnResult();	
	}
	
	@Test
	void givenInvalidTopicId_whenDetailTopic_mustReturnNotFound() {
		webTestClient.get()
		.uri("/topics/5")
		.exchange()
		.expectStatus()
		.isNotFound();	
	}
	
	@Test
	void givenValidTopicFormAndExistentMediaAtDB_whenRegisterTopic_thenMustReturnCreated() {
		
		TopicForm form = new TopicForm();
		form.setAuthorId("1");
		form.setDescription("Topic test description");
		form.setMediaName("CAPTAIN MARVEL");
		form.setTitle("Topic test title");
		
		webTestClient.post()
		.uri("/topics/")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),TopicForm.class)
		.exchange()
		.expectStatus()
		.isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(TopicDto.class)
		;
	}
	
	@Test
	void givenValidTopicFormAndInexistentMediaAtDB_whenRegisterTopic_thenMustReturnCreated() {
		
		TopicForm form = new TopicForm();
		form.setAuthorId("1");
		form.setDescription("Topic test description");
		form.setMediaName("Harry Potter");
		form.setTitle("Topic test title");
		
		webTestClient.post()
		.uri("/topics/")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),TopicForm.class)
		.exchange()
		.expectStatus()
		.isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(TopicDto.class)
		;
	}
	
	@Test
	void givenInvalidTopicFormAndInexistentMediaAtDB_whenRegisterTopic_thenMustReturnBadRequest() {
		
		TopicForm form = new TopicForm();
		form.setAuthorId("1");
		form.setDescription("Topic test description");
		form.setMediaName("Not a media at Extern API");
		form.setTitle("Topic test title");
		
		webTestClient.post()
		.uri("/topics/")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(form),TopicForm.class)
		.exchange()
		.expectStatus()
		.isBadRequest()
		;
	}
	
	@Test
	void givenValidTopicId_whenCloseTopic_mustReturnOk() {
		webTestClient.put()
		.uri("/topics/3")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody(TopicDto.class).returnResult();
	}
	
	@Test
	void givenInvalidTopicId_whenCloseTopic_mustReturnNotFound() {

		webTestClient.put()
		.uri("/topics/5")
		.exchange()
		.expectStatus()
		.isNotFound();
	}
	
	@Test
	void givenValidTopicId_whenDeleteTopic_mustReturnNoContent() {

		webTestClient.delete()
		.uri("/topics/4")
		.exchange()
		.expectStatus()
		.isNoContent();
	}
	
	@Test
	void givenInexistentTopicId_whenDeleteTopic_mustReturnNotFound() {
		webTestClient.delete()
		.uri("/topics/5")
		.exchange()
		.expectStatus()
		.isNotFound();
	}
	
	@Test
	void givenRepliedTopicId_whenDeleteTopic_mustReturnBadRequest() {
		webTestClient.delete()
		.uri("/topics/2")
		.exchange()
		.expectStatus()
		.isBadRequest();
	}
	
	@Test
	void givenClosedTopicId_whenDeleteTopic_mustReturnBadRequest() {
		webTestClient.delete()
		.uri("/topics/3")
		.exchange()
		.expectStatus()
		.isBadRequest();
	}
	
}
