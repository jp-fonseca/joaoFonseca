package br.com.idp.JoaoFonseca.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.idp.JoaoFonseca.dto.MediaDto;

/**
 * This is a class that represents a Media
 */
@Entity
public class Media {

	/**
	 * The id of the media.
	 */
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * The title of the media.
	 */
	@NotNull
	private String title;
	
	/**
	 * The score (rating) of the Media at imdb.
	 */
	private Double score;
	
	/**
	 * The release year of the media (or timelapse of it, e.g: tv shows).
	 */
	private String releaseYear;
	
	/**
	 * The genre(s) of the media.
	 */
	private String genre;
	
	public Media() {
	}
	
	/**
	 * This is a way to set a Media given the specific Title, Score, Genre and ReleaseYear.
	 */
	public Media(String title, Double score, String genre, String releaseYear) {
		this.title = title;
		this.score = score;
		this.genre = genre;
		this.releaseYear = releaseYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Long getId() {
		return id;
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
		Media other = (Media) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Title: " + title + " Year: " + releaseYear + " Genre: " + genre + " Score: " + score;
	}

	public static Media convertOneMedia(MediaDto newDtoMedia) {
		return null;
	}
}
