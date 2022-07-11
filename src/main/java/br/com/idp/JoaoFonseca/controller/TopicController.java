package br.com.idp.JoaoFonseca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.idp.JoaoFonseca.dto.DetailedTopicDto;
import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.service.TopicService;

@RestController
@RequestMapping("/topics")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@GetMapping
	public ResponseEntity<List<TopicDto>> listAll(@RequestParam(required = false) String mediaName){
		List<TopicDto> topics = topicService.listAll(mediaName);
		return ResponseEntity.ok(topics);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailedTopicDto> detailOneTopic(@PathVariable Long id){
		Optional<Topic> topic = topicService.detailOne(id);
		if(topic.isPresent()) {
			return ResponseEntity.ok(new DetailedTopicDto(topic.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<TopicDto> registerTopic(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder){
		Topic topic = topicService.register(form);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}
	
}
