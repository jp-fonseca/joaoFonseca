package br.com.idp.JoaoFonseca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.model.TopicStatus;
import br.com.idp.JoaoFonseca.repository.TopicRepository;

@SpringBootTest
@ActiveProfiles("test")
class TopicServiceTest {

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Test
	void givenNoMediaName_whenListAllMethodInvoqued_thenOneListWithAllMediasIsReturned() {
		List<TopicDto> list = topicService.listAll(null);
		assertEquals(list.size(), 4); //4 is the number of Topics existent in H2 DB.
	}
	
	@Test
	void givenValidMediaName_whenListAllMethodInvoqued_thenOneListWithSpecifiedMediasIsReturned() {
		String mediaName = "CAPTAIN MARVEL";	
		List<TopicDto> list = topicService.listAll(mediaName);
		assertEquals(list.get(0).getMedia(), mediaName);
	}
	
	@Test
	void givenInvalidMediaName_whenListAllMethodInvoqued_thenEmptyListIsReturned() {
		String mediaName = "NOT A MEDIA AT ALL";	
		List<TopicDto> list = topicService.listAll(mediaName);
		assertEquals(list, Collections.EMPTY_LIST);
	}
	
	@Test
	void givenValidTopicForm_whenRegisterMethodInvoqued_thenATopicIsCreated() {

		TopicForm form = new TopicForm();
		form.setAuthorId("1");
		form.setDescription("Valid Description");
		form.setMediaName("Harry Potter and the Deathly Hallows: Part 2");
		form.setTitle("Valid Title");
		
		Topic newTopic = topicService.register(form);
		
		assertEquals(Long.parseLong(form.getAuthorId()), newTopic.getAuthor().getId());
		assertEquals(form.getDescription(),newTopic.getDescription());
		assertEquals(form.getMediaName(), newTopic.getMedia().getTitle());
		assertEquals(form.getTitle(), newTopic.getTitle());
		
	}
	
	@Test
	void givenInvalidTopicForm_whenRegisterMethodInvoqued_thenIllegalArgumentExceptionIsThrown() {
		TopicForm form = new TopicForm();
		form.setAuthorId("");
		form.setDescription("");
		form.setMediaName("");
		form.setTitle("");		
		assertThrows(IllegalArgumentException.class, () -> {
			topicService.register(form);
		});
	}
	
	@Test
	void givenAValidTopic_whenChangeStatusMethodInvoqued_thenChangeTopicStatusToReplied() {
		Long id = (long) 1;
		Optional<Topic> topic = topicRepository.findById(id);
		topicService.changeStatus(topic);
		assertEquals(TopicStatus.REPLIED, topic.get().getStatus());
	}
	
	@Test
	void givenAnIvalidTopic_whenChangeStatusMethodInvoqued_thenNoSuchElementException() {
		Long id = (long) 6;
		Optional<Topic> topic = topicRepository.findById(id);
		assertThrows(NoSuchElementException.class, () -> {
			topicService.changeStatus(topic);
		});
	}
	
	@Test
	void givenAValidTopic_whenCloseTopicMethodInvoqued_thenChangeTopicStatusToClosed() {
		Long id = (long) 1;
		Optional<Topic> topic = topicRepository.findById(id);
		topicService.closeTopic(topic);
		assertEquals(TopicStatus.CLOSED, topic.get().getStatus());
	}
	
	@Test
	void givenAnIvalidTopic_whenCloseTopicMethodInvoqued_thenNoSuchElementException() {
		Long id = (long) 6;
		Optional<Topic> topic = topicRepository.findById(id);
		assertThrows(NoSuchElementException.class, () -> {
			topicService.changeStatus(topic);
		});
	}
	
	
}
