package br.com.idp.JoaoFonseca.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This is a class that represents a Topic.
 */
@Entity
public class Topic {

	/**
	 * The id of the Topic.
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * The title of the Topic.
	 */
	private String title;

	/**
	 * The description of the Topic.
	 */
	private String description;
	
	/**
	 * The status of the Topic.
	 */
	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.NOT_REPLIED;
	
	/**
	 * The Media associated with the Topic.
	 */
	@ManyToOne
	@NotNull
	private Media media;
	
	/**
	 * The Author of the Topic.
	 */
	@ManyToOne
	@NotNull
	private Author author;
	
	/**
	 * The Reply(replies) of the Topic.
	 */
	@OneToMany(mappedBy = "topic")
	private List<Reply> replies = new ArrayList<>();

	
	public Topic() {
	}
	
	/**
	 * This is a constructor to set a Topic with the given Media, Title, Description, Reply(replies) and Author.
	 */
	public Topic(Media media, String title, String description, List<Reply> replies, Author author) {
		this.media = media;
		this.title = title;
		this.description = description;
		this.replies = replies;
		this.author = author;
	}

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

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
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
	
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
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
		Topic other = (Topic) obj;
		return Objects.equals(id, other.id);
	}	
	
}
