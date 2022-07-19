package br.com.idp.JoaoFonseca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.repository.MediaRepository;

/**
 * This class is responsible for all the services related to Medias.
 */
@Service
public class MediaService {

	@Autowired
	private MediaRepository mediaRepository;

	/**
	 * This method list all Medias.
	 * @return All Medias.
	 */
	@Cacheable(value = "allMedias")
	public List<MediaDto> listAll() {
		List<Media> medias = mediaRepository.findAll();
		return MediaDto.convert(medias);
	}
	
	/**
	 * This method show one Media.
	 * @param title The Title of the Media.
	 * @return A specific Media.
	 */
	public Media findOne(String title) {
		Media media = mediaRepository.findByTitle(title);
		if (media == null) {
			return null;
		} else {
			return media;
		}

	}
	
	/**
	 * This method register a Media.
	 * @param MediaDto - A DTO of Media.
	 * @return The Media registered.
	 */
	@Transactional
	@CacheEvict(value = "allMedias", allEntries = true)
	public Media register(MediaDto newDtoMedia) {
		Media newMedia = new Media(newDtoMedia.getTitle(), newDtoMedia.getImdbRating(), newDtoMedia.getGenre(), newDtoMedia.getYear()); 
		mediaRepository.save(newMedia);
		return newMedia;
	}

}
