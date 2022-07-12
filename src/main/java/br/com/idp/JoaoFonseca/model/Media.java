package br.com.idp.JoaoFonseca.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.idp.JoaoFonseca.dto.MediaDto;

@Entity
public class Media {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Double score;
	private Integer releaseYear;
	private String genre;
	
	public Media() {
	}
	
	public Media(String title, Double score, String genre, Integer releaseYear) {
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

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
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
