package br.com.idp.JoaoFonseca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.idp.JoaoFonseca.DTO.AuthorDTO;
import br.com.idp.JoaoFonseca.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<AuthorDTO>> list(@RequestParam(required = false) String name){
		List<AuthorDTO> authorsList= authorService.listAuthors(name);
		if (authorsList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Authors not found");
		}else {
			return ResponseEntity.ok(authorsList);
		}
	}
	
}
