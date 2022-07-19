package br.com.idp.JoaoFonseca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.AuthorDto;
import br.com.idp.JoaoFonseca.model.Author;
import br.com.idp.JoaoFonseca.repository.AuthorRepository;

/**
 * This class is responsible for all the services related to Authors.
 */
@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	/**
	 * This method list all Authors.
	 * @param Name The name of an Author - It's optional
	 * @return All Authors (if name not informed) or Author(s) with name informed.
	 */
	public List<AuthorDto> listAuthors(String name) {
		if(name == null) {
			List<Author> author = authorRepository.findAll();
			return AuthorDto.convert(author);
		}else {
			Author author = authorRepository.findByName(name);
			return AuthorDto.convert(author);
		}

	}
	
	
}
