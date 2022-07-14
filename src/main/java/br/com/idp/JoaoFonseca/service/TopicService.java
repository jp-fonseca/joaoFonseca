package br.com.idp.JoaoFonseca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;
import br.com.idp.JoaoFonseca.repository.TopicRepository;
import br.com.idp.JoaoFonseca.service.adapter.WebClientOmdbApi;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
		
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private WebClientOmdbApi webClient;
	

	public List<TopicDto> listAll(String name) {
		if (name == null) {
			List<Topic> topics = topicRepository.findAll();
			return TopicDto.convert(topics);
		}else {
			List<Topic> topic = topicRepository.findByMediaTitle(name);
			return TopicDto.convert(topic);
		}
	}

	@Transactional
	public Topic register(@Valid TopicForm form) {
		
		String title = form.getMediaName();
		
		Media media = mediaService.findOne(title);
		
		if(media != null) {
		Topic topic = form.convert(media, authorRepository);
		topicRepository.save(topic);
		return topic;
		}else {
			MediaDto newDtoMedia = webClient.getMedia(title);
			if(newDtoMedia == null) {
				return null;			
			}
			Media newMedia = mediaService.register(newDtoMedia);
			Topic topic = form.convert(newMedia, authorRepository);
			topicRepository.save(topic);
			return topic;
		}
		
	}

	public Optional<Topic> detailOne(Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		return topic;
	}

}
