package br.com.idp.JoaoFonseca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.repository.MediaRepository;

@Service
public class MediaService {

	@Autowired
	private MediaRepository mediaRepository;

	public List<MediaDto> listAll() {
		List<Media> medias = mediaRepository.findAll();
		return MediaDto.convert(medias);
	}

	public Media findOne(String title) {
		Media media = mediaRepository.findByTitle(title);
		if (media == null) {
			return null;
		} else {
			return media;
		}

	}
	
	@Transactional
	public Media register(MediaDto newDtoMedia) {
		Media newMedia = new Media(newDtoMedia.getTitle(), newDtoMedia.getImdbRating(), newDtoMedia.getGenre(), newDtoMedia.getYear()); 
		mediaRepository.save(newMedia);
		return newMedia;
	}

}
