package br.com.idp.JoaoFonseca.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.idp.JoaoFonseca.model.Media;


public class MediaDto {

	private String name;
	private Double score;
	private String genre;
	private LocalDate date;
	
	public MediaDto(Media media) {
		this.name = media.getName();
		this.score = media.getScore();
		this.genre = media.getGenre();
		this.date = media.getDate();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public static List<MediaDto> convert(List<Media> medias) {
		return medias.stream().map(MediaDto::new).collect(Collectors.toList());
	}
	
	
	
	
}
