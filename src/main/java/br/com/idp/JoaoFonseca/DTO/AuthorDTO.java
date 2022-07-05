package br.com.idp.JoaoFonseca.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.idp.JoaoFonseca.model.Author;

public class AuthorDTO {

	
	private String name;
	private String email;
	
	public AuthorDTO() {

	}
	
	public AuthorDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public AuthorDTO(Author author) {
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

	public static List<AuthorDTO> convert(List<Author> author) {
		return author.stream().map(AuthorDTO::new).collect(Collectors.toList());
	}

	public static List<AuthorDTO> convert(Author author) {
		List<AuthorDTO> authorDTO = new ArrayList<>();
		authorDTO.add(new AuthorDTO(author));
		return authorDTO;
	}

}
