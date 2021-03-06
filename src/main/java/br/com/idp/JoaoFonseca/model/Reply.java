package br.com.idp.JoaoFonseca.model;

import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * This is a class that represents a reply.
 */
@Entity
public class Reply {
	
	/**
	 * The id of the reply.
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * The text of the reply.
	 */
	private String description;
	
	
	/**
	 * The author associated with the reply.
	 */
	@ManyToOne
	@NotNull
	private Author author;
	
	/**
	 * The topic wich the reply is in.
	 */
	@ManyToOne
	@NotNull
	private Topic topic;
	
	public Reply() {
	}
	
	/**
	 * This is a constructor of to set a reply with the given Topic, Description and Author.
	 */
	public Reply(Topic topic,String description, Author author) {
		this.topic = topic;
		this.description = description;
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reply other = (Reply) obj;
		return Objects.equals(id, other.id);
	}
	
	
}

