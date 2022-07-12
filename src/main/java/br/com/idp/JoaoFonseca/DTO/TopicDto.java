package br.com.idp.JoaoFonseca.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.idp.JoaoFonseca.model.Topic;

public class TopicDto {

	private Long id;
	private String title;
	private String mediaName;
	private String status;
	
	public TopicDto (Topic topic) {
	this.id = topic.getId();
	this.title = topic.getTitle();
	this.mediaName = topic.getMedia().getTitle();
	this.status = topic.getStatus();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMedia() {
		return mediaName;
	}

	public String getStatus() {
		return status;
	}

	public static List<TopicDto> convert(List<Topic> topics) {
		return topics.stream().map(TopicDto::new).collect(Collectors.toList());
	}

	public static List<TopicDto> convert(Topic topic) {
		List<TopicDto> topics = new ArrayList<>();
		topics.add(new TopicDto(topic));
		return topics;
	}
	
	
	
	
	
	
}
