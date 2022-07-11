package br.com.idp.JoaoFonseca.dto;

import br.com.idp.JoaoFonseca.model.Reply;

public class ReplyDto {

	private String description;
	private String authorName;
	private String topicTitle;
	
	public ReplyDto(Reply reply) {
		this.description = reply.getDescription();
		this.authorName = reply.getAuthor().getName();
		this.topicTitle = reply.getTopic().getTitle();
	}

	public String getDescription() {
		return description;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getTopicTitle() {
		return topicTitle;
	}
	
}
