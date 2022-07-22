package br.com.idp.JoaoFonseca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.model.Media;

@SpringBootTest
@ActiveProfiles("test")
class MediaServiceTest {

	@Autowired
	private MediaService mediaService;

	@Test
	void givenValidMediaDto_whenRegisterMethodInvoqued_thenAMediaIsReturned() {

		MediaDto mediaDto = new MediaDto();
		mediaDto.setGenre("Horror");
		mediaDto.setImdbRating(4.9);
		mediaDto.setTitle("Rosemary's Baby");
		mediaDto.setYear("1968");

		Media newMedia = mediaService.register(mediaDto);

		assertEquals(newMedia.getGenre(), mediaDto.getGenre());
		assertEquals(newMedia.getScore(), mediaDto.getImdbRating());
		assertEquals(newMedia.getTitle(), mediaDto.getTitle());
		assertEquals(newMedia.getReleaseYear(), mediaDto.getYear());
	}

	@Test
	void givenInvalidMediaDto_whenRegisterMethodInvoqued_thenAConstraintViolationExceptionIsThrown() {

		MediaDto mediaDto = new MediaDto(); //It's null.
		assertThrows(ConstraintViolationException.class, () -> {
			mediaService.register(mediaDto);
		});
	}

}
