package br.com.idp.JoaoFonseca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.idp.JoaoFonseca.dto.AuthorDto;

@SpringBootTest
@ActiveProfiles("test")
class AuthorServiceTest {

	@Autowired
	private AuthorService authorService;
	
	@Test
	void givenNoAuthorName_whenListAuthorsMethodInvoqued_thenOneListWithAllAuthorsIsReturned() {
		List<AuthorDto> list = authorService.listAuthors(null);
		assertEquals(list.size(), 3); //3 is the number of authors existent in H2 DB.
	}
	
	@Test
	void givenValidAuthorName_whenListAuthorsMethodInvoqued_thenOneListWithSpecifiedMediasIsReturned() {
		String name = "Joao Paulo";	
		
		List<AuthorDto> list = authorService.listAuthors(name);
		assertEquals(list.get(0).getName(), name);
	}
	
	@Test
	void givenInvalidMediaName_whenListAuthorsInvoqued_thenNullPointerExceptionIsThrown() {
		String name = "Not an Author";	
		assertThrows(NullPointerException.class,() ->{
			authorService.listAuthors(name);			
		});
	}

}
