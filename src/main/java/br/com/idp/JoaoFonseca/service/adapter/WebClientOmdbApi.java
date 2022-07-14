package br.com.idp.JoaoFonseca.service.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import reactor.core.publisher.Mono;

@Service
public class WebClientOmdbApi {

	private String omdbApi = "http://www.omdbapi.com";

	public MediaDto getMedia(String title) {
		
		String request = "/?i=tt3896198&apikey=79ce98e8&t=" + title;
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
