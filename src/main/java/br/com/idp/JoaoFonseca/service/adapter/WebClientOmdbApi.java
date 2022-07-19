package br.com.idp.JoaoFonseca.service.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import reactor.core.publisher.Mono;

/**
 * This class is an adapter to comunicate with extern API.
 */
@Service
public class WebClientOmdbApi {

	@Value("${omdb.api.url}")
	private String omdbApi;

	/**
	 * This method is responsible for getting Data from a Media at the extern API.
	 */
	public MediaDto getMedia(String title) {
		String request = "?i=tt3896198&apikey=79ce98e8&t=" + title;
			Mono<MediaDto> monoMedia = WebClient.create(omdbApi)
					.get()
					.uri(request)
					.retrieve()
					.bodyToMono(MediaDto.class);
			monoMedia.subscribe();
			
			MediaDto newMedia = monoMedia.block();
			if (newMedia.getTitle() != null) {
				return newMedia;
			}
			return null;
	}

}
