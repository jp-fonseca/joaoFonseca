package br.com.idp.JoaoFonseca.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.model.TopicStatus;

public class DetailedTopicDto {

	private Long id;
	private String title;
	private String description;
	private TopicStatus status;
	private String mediaName;
	private String authorName;
	private List<ReplyDto> replies;
	
	public DetailedTopicDto() {
	
	}

	public DetailedTopicDto(Topic topic) {
	this.id = topic.getId();
	this.title = topic.getTitle();
	this.description = topic.getDescription();
	this.status = topic.getStatus();
	this.mediaName = topic.getMedia().getTitle();
	this.authorName = topic.getAuthor().getName();
	
	this.replies = new ArrayList<>();
	this.replies.addAll(topic.getReplies().stream().map(ReplyDto::new).collect(Collectors.toList()));
	}


	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public String getMediaName() {
		return mediaName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public List<ReplyDto> getReplies() {
		return replies;
	}
	
}
