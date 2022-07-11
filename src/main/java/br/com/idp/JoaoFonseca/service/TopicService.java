package br.com.idp.JoaoFonseca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;
import br.com.idp.JoaoFonseca.repository.MediaRepository;
import br.com.idp.JoaoFonseca.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	

	public List<TopicDto> listAll(String name) {
		if (name == null) {
			List<Topic> topics = topicRepository.findAll();
			return TopicDto.convert(topics);
		}else {
			List<Topic> topic = topicRepository.findByMediaName(name);
			return TopicDto.convert(topic);
		}
	}

	@Transactional
	public Topic register(@Valid TopicForm form) {
		Topic topic = form.convert(mediaRepository, authorRepository);
		topicRepository.save(topic);
		return topic;
		
	}

	public Optional<Topic> detailOne(Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		return topic;
	}

}
