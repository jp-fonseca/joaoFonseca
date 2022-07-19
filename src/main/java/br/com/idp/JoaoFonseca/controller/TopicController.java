package br.com.idp.JoaoFonseca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.idp.JoaoFonseca.dto.DetailedTopicDto;
import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.service.TopicService;

/**
 * This is a class that represents a Controller (Topic).
 */
@RestController
@RequestMapping("/topics")
public class TopicController {

	@Autowired
	private TopicService topicService;

	/**
	 * This method list All Topics.
	 * @return ResponseEntity.ok(topics)
	 */
	@GetMapping
	public ResponseEntity<List<TopicDto>> listAll(@RequestParam(required = false) String mediaName) {
		List<TopicDto> topics = topicService.listAll(mediaName);
		return ResponseEntity.ok(topics);
	}
	
	/**
	 * This method details a Topic by ID.
	 * @param id The ID of the Topic
	 * @return ResponseEntity.ok(topics) in case of success;
	 * @throws ResponseStatusException(HttpStatus.NOT_FOUND)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DetailedTopicDto> detailOneTopic(@PathVariable Long id) {
		Optional<Topic> topic = topicService.detailOne(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailedTopicDto(topic.get()));
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found.");
	}
	
	/**
	 * This method register a new Topic.
	 * @param form TopicForm with Data to be validated.
	 * @return ResponseEntity.created(uri).body(TopicDTO) in case of success;
	 * @throws ResponseStatusException(HttpStatus.BAD_REQUEST)
	 */
	@PostMapping
	public ResponseEntity<TopicDto> registerTopic(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = topicService.register(form);
		if (topic == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Media not found at extern API.");
		}
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	/**
	 * This method closes a Topic by ID.
	 * @param id The ID of the Topic
	 * @return ResponseEntity.ok(topic) in case of success;
	 * @throws ResponseStatusException(HttpStatus.NOT_FOUND)
	 */
	@PutMapping("/{id}")
	public ResponseEntity<TopicDto> closeTopic(@PathVariable Long id) {
		Optional<Topic> topic = topicService.detailOne(id);
		if (topic.isPresent()) {
			topicService.closeTopic(topic);
			return ResponseEntity.ok(new TopicDto(topic.get()));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found.");
		}
	}

}
