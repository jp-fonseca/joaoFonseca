package br.com.idp.JoaoFonseca.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.idp.JoaoFonseca.model.Media;


public class MediaDto {

	@JsonAlias("Title")
	private String title;
	
	private Double imdbRating;
	
	@JsonAlias("Genre")
	private String genre;
	
	@JsonAlias("Year")
	private String year;
	
	public MediaDto() {
	}
	
	public MediaDto(Media media) {
		this.title = media.getTitle();
		this.imdbRating = media.getScore();
		this.genre = media.getGenre();
		this.year = media.getReleaseYear();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}
	public Double getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(Double score) {
		this.imdbRating = score;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String date) {
		this.year = date;
	}
	
	public static List<MediaDto> convert(List<Media> medias) {
		return medias.stream().map(MediaDto::new).collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return "Title: " + title + "Score:" + imdbRating + "Genre: " + genre + "Date: " + year;
	}


}
