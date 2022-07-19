package br.com.idp.JoaoFonseca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.dto.TopicDto;
import br.com.idp.JoaoFonseca.form.TopicForm;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.model.TopicStatus;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;
import br.com.idp.JoaoFonseca.repository.TopicRepository;
import br.com.idp.JoaoFonseca.service.adapter.WebClientOmdbApi;

/**
 * This class is responsible for all the services related to Topics.
 */
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
	
	/**
	 * This method list all Topics
	 * @param Name the Title of the media - It's optional
	 * @return All Topics (if name not informed) or Topics wich Title = param informed.
	 */
	@Cacheable(value = "allTopics")
	public List<TopicDto> listAll(String name) {
		if (name == null) {
			List<Topic> topics = topicRepository.findAll();
			return TopicDto.convert(topics);
		}else {
			List<Topic> topic = topicRepository.findByMediaTitle(name);
			return TopicDto.convert(topic);
		}
	}
	
	/**
	 * This method register a Topic.
	 * It validates if the Media is already at Local Database; If not, it'll get Data from extern API.
	 * @param form TopicForm to be validated.
	 * @return Topic created or null in case of not success.
	 */
	@Transactional
	@CacheEvict(value = "allTopics", allEntries = true)
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

	/**
	 * This method is used to Detail a Topic (with all the replies of it)
	 * @param id The ID of the topic.
	 * @return Topic
	 */
	public Optional<Topic> detailOne(Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		return topic;
	}
	
	/**
	 * This method is used to change the Status of the topic to REPLIED.
	 */
	@Transactional
	public void changeStatus(Optional<Topic> topic) {
		topic.get().setStatus(TopicStatus.REPLIED);		
	}

	/**
	 * This method is used to change the Status of the topic to CLOSED.
	 */
	@Transactional
	public void closeTopic(Optional<Topic> topic) {
		topic.get().setStatus(TopicStatus.CLOSED);
	}

}
