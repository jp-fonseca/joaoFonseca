package br.com.idp.JoaoFonseca.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.idp.JoaoFonseca.model.Author;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;
import br.com.idp.JoaoFonseca.repository.MediaRepository;

public class TopicForm {

	@NotNull @NotEmpty
	private String title;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String status;
	
	@NotNull @NotEmpty
	private String mediaName;
	
	@NotNull @NotEmpty @Email
	private String authorEmail;
	
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
	
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public Topic convert(MediaRepository mediaRepository, AuthorRepository authorRepository) {
		Media media = mediaRepository.findByName(mediaName);
		Author author = authorRepository.findByEmail(authorEmail);
		return new Topic(media, title, description, status, null, author); 
	}
	
}
