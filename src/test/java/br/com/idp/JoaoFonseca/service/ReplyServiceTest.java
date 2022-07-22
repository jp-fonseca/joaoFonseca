package br.com.idp.JoaoFonseca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import br.com.idp.JoaoFonseca.form.ReplyForm;
import br.com.idp.JoaoFonseca.model.Reply;

@SpringBootTest
@ActiveProfiles("test")
class ReplyServiceTest {

	@Autowired
	private ReplyService replyService;

	@Test
	void givenValidReplyFormAndTopicNotClosed_whenRegisterMethodInvoqued_thenAReplyIsCreated() {

		ReplyForm form = new ReplyForm();
		form.setAuthorId("1");
		form.setDescription("This is a valid form and the topic is not closed.");
		Reply reply = replyService.register((long) 2, form); // Topic with #id 2 is not closed.

		assertEquals(reply.getAuthor().getId(), Long.parseLong(form.getAuthorId()));
		assertEquals(reply.getDescription(), form.getDescription());
	}

	
	@Test
	void givenValidReplyFormAndTopicIsClosed_whenRegisterMethodInvoqued_thenResponseStatusExceptionIsThrown() {

		ReplyForm form = new ReplyForm();
		form.setAuthorId("1");
		form.setDescription("This is a valid form, but the topic is closed.");
		
		assertThrows(ResponseStatusException.class, () -> {
			replyService.register((long) 3, form); // Topic with #id 3 is closed.
		});
	}
	
	@Test
	void givenInvalidReplyFormAndTopicNotClosed_whenRegisterMethodInvoqued_thenIllegalArgumentExceptionIsThrown() {
		
		ReplyForm form = new ReplyForm();
		form.setAuthorId("");
		form.setDescription("");
		
		assertThrows(IllegalArgumentException.class, () -> {
			replyService.register((long) 2, form);
		});
	}
	
	@Test
	void givenInvalidReplyFormAndTopicisClosed_whenRegisterMethodInvoqued_thenResponseStatusExceptionIsThrown() {
		
		ReplyForm form = new ReplyForm();
		form.setAuthorId("");
		form.setDescription("");
		
		assertThrows(ResponseStatusException.class, () -> {
			replyService.register((long) 3, form);
		});
	}
	

}
