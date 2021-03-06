package br.com.idp.JoaoFonseca.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.idp.JoaoFonseca.form.ReplyForm;
import br.com.idp.JoaoFonseca.model.Reply;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.model.TopicStatus;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;
import br.com.idp.JoaoFonseca.repository.ReplyRepository;
import br.com.idp.JoaoFonseca.repository.TopicRepository;

/**
 * This class is responsible for all the services related to Replies.
 */
@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private TopicService topicService;
	
	/**
	 * This method register a Reply.
	 * It validates if the Topic exists at Local Database or is CLOSED before registering.
	 * @param form ReplyForm to be validated.
	 * @return Reply created or null in case of not success.
	 */
	@Transactional
	public Reply register(Long id, ReplyForm form) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.get().getStatus() == TopicStatus.CLOSED) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Topic is closed.");
		} else if (topic.isPresent()) {
			topicService.changeStatus(topic);
			Reply reply = form.convert(topic.get(), authorRepository);
			replyRepository.save(reply);
			topic.get().getReplies().add(reply);
			return reply;
		} else {
			return null;
		}

	}

}
