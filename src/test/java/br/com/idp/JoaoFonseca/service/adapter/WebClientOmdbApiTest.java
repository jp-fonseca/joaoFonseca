package br.com.idp.JoaoFonseca.service.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.idp.JoaoFonseca.dto.MediaDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class WebClientOmdbApiTest {

	@Autowired
	private WebClientOmdbApi webClientOmdbApi;
	
	@Value("${omdb.api.url}")
	private String omdbApi;
	
	@Test
	public void mustReturnNullIfTitleDoesntExists() {
		String title = "Neque porro quisquam est qui dolorem";
		MediaDto media = webClientOmdbApi.getMedia(title);
		assertEquals(media, null);
	}
	
	@Test
	public void mustReturnDataOfMediaIfTitleExists() {
		String title = "Titanic";
		MediaDto media = webClientOmdbApi.getMedia(title);
		assertEquals(media.getTitle(), "Titanic");
		assertEquals(media.getGenre(), "Drama, Romance");
		assertEquals(media.getImdbRating(), 7.9);
		assertEquals(media.getYear(), "1997");
	}

}
