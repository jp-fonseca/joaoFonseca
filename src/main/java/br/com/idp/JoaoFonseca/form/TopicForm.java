package br.com.idp.JoaoFonseca.form;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.idp.JoaoFonseca.model.Author;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.model.Reply;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;

public class TopicForm {

	@NotNull @NotEmpty
	private String title;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String status;
	
	@NotNull @NotEmpty
	private String mediaName;
	
	@NotNull @NotEmpty 
	private String authorId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	
	
	public Topic convert(Media media, AuthorRepository authorRepository) {
		Long id = Long.parseLong(authorId);
		Optional<Author> author = authorRepository.findById(id);
		if(author.isPresent()) {
			Author presentAuthor = author.get();
			return new Topic(media, title, description, status, new ArrayList<Reply>(), presentAuthor);
		}else {
			throw new RuntimeException();
		} 
	}
	
}
