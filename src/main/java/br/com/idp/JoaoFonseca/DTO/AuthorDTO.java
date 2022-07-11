package br.com.idp.JoaoFonseca.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.idp.JoaoFonseca.model.Author;

public class AuthorDto {

	
	private String name;
	private String email;
	
	public AuthorDto() {

	}
	
	public AuthorDto(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public AuthorDto(Author author) {
		this.name = author.getName();
		this.email = author.getEmail();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static List<AuthorDto> convert(List<Author> author) {
		return author.stream().map(AuthorDto::new).collect(Collectors.toList());
	}

	public static List<AuthorDto> convert(Author author) {
		List<AuthorDto> authorDto = new ArrayList<>();
		authorDto.add(new AuthorDto(author));
		return authorDto;
	}

}
