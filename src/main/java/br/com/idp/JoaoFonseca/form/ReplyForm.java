package br.com.idp.JoaoFonseca.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.idp.JoaoFonseca.model.Author;
import br.com.idp.JoaoFonseca.model.Reply;
import br.com.idp.JoaoFonseca.model.Topic;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;

/**
 * This class represents a Form for the Reply.
 */
public class ReplyForm {
	
	/**
	 * The Description of The Reply. 
	 * Can't be Null nor Empty.
	 */
	@NotNull @NotEmpty
	private String description;
	
	/**
	 * The AuthorID of Reply. 
	 * Can't be Null nor Empty.
	 */
	@NotNull @NotEmpty
	private String authorId;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Reply convert(Topic topic, AuthorRepository authorRepository) {
		Long id = Long.parseLong(authorId);
		Optional<Author> author = authorRepository.findById(id);
		if(author.isPresent()) {
			return new Reply(topic, description, author.get());
		}else {
			throw new RuntimeException();
		}
	}

}
